package com.zstwp.mans.kafka;

import com.zstwp.mans.domain.database.entities.AlertStatus;
import com.zstwp.mans.domain.database.entities.User;
import com.zstwp.mans.domain.database.entities.UserSpecialization;
import com.zstwp.mans.domain.dto.AlertDto;
import com.zstwp.mans.domain.dto.UserDto;
import com.zstwp.mans.domain.mappers.AlertMapper;
import com.zstwp.mans.domain.services.AlertService;
import com.zstwp.mans.domain.services.EmailService;
import com.zstwp.mans.domain.services.UserService;
import com.zstwp.mans.domain.services.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaAlertHandler {

    private final EmailService emailService;

    private final AlertService alertService;

    private final UserService userService;

    private final AlertMapper alertMapper;


    private static final Random PRNG = new Random();

    public void handleNewAlert(KafkaAlertDto kafkaAlertDto) {
        /*
        check for duplicated alerts (agent side??!)
        select user -> user with required specialisation + fewest alerts assigned
        send email
        add alert to the db
         */

//        map kafka alert to alert dto (kafkaAlertDto - no id, no userDto)
        AlertDto alertDto = alertMapper.toAlertDto(kafkaAlertDto);

//        determine specialization
        UserSpecialization specialization = UserSpecialization.values()[PRNG.nextInt(22) % UserSpecialization.values().length];
        System.out.println("specialization: " + specialization.toString());

        try {
            User user = userService.getUserBySpecializationAndFewestAlerts(specialization.toString());
            alertDto.setUserDto(new UserDto(user));
            alertDto.setStatus(AlertStatus.ASSIGNED);

            System.out.println(alertDto.toString());

//            emailService.sendEmail(user.getEmail(), "New alert!", alertDto.getDescription());
            log.info("email sent to: " + user.getEmail());

        } catch (UserNotFoundException e) {
            log.info("No user found with specialization " + specialization.toString());
            alertDto.setStatus(AlertStatus.UNASSIGNED);

        } finally {
            alertService.addNewAlert(alertDto);
            log.info("Success! " + alertDto.toString());
        }
    }
}
