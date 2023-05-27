package com.zstwp.mans.services;

import com.zstwp.mans.database.entities.AlertStatus;
import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.dto.AlertDto;
import com.zstwp.mans.services.mappers.GetAlertSpecialization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlertHandler {

    private final EmailService emailService;

    private final AlertService alertService;

    private final UserService userService;

    public void handleNewAlert(AlertDto alertDto) {
//        select user -> user with required specialisation + fewest alerts assigned
//        send email
//        add new alert (db)

        if(false) {
//            na jakiej zasadzie rozrozniamy alerty?
        }

        User user =  userService.getUserBySpecializationAndFewestAlerts(
                GetAlertSpecialization.getRandomSpecialization().toString());

        if(user != null) {
            emailService.sendEmail(
                    user.getEmail(),
                    "New alert!",
                    "Hello " + user.getFirstName() + ", a new alert has been assigned to you!"
            );

            alertDto.setStatus(AlertStatus.IN_PROGRESS);
        }

        alertService.addNewAlert(alertDto);
    }
}
