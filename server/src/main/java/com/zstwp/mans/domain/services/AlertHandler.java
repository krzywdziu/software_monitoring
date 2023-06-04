package com.zstwp.mans.domain.services;

import com.zstwp.mans.domain.database.entities.AlertStatus;
import com.zstwp.mans.domain.database.entities.User;
import com.zstwp.mans.domain.dto.AlertDto;
import com.zstwp.mans.domain.mappers.GetAlertSpecialization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlertHandler {

    private final EmailService emailService;

    private final AlertService alertService;

    private final UserService userService;

    private String previousData;

    private boolean emailSent = false;

    private static final int ALERTS_MAX = 5;

    private int alertCount = 0;

    public void handleNewAlert(AlertDto alertDto) {
//        check for duplicated alerts
//        select user -> user with required specialisation + fewest alerts assigned
//        send email
//        add new alert (db)

//        if(previousData == null && data != null) previousData = data;

//        if(!previousData.equals(data)) {


        if(alertCount <= ALERTS_MAX) {
            alertCount++;
            System.out.println(alertCount);

            System.out.println("alert handling");
//            AlertDto alertDto = dataMapper.mapData(data);

            User user =  userService.getUserBySpecializationAndFewestAlerts(
                    GetAlertSpecialization.getRandomSpecialization().toString());

            if(user != null) {

//                if(!emailSent) {
                if(true) {
                    System.out.println("mailing to marekrospong@gmail.com");
                    emailService.sendEmail(
//                            user.getEmail(),
                            "mailAddr",
                            "New alert!",
                            "Hello " + user.getFirstName() + ", a new alert has been assigned to you!"
                    );
                    emailSent = true; //avoid spamming emails
                }
                alertDto.setStatus(AlertStatus.IN_PROGRESS);
            }

            alertService.addNewAlert(alertDto);
        }
    }
}
