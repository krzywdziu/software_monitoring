package com.zstwp.mans.services;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class AlertService {

    private static final String TOPIC = "mans_topic";

    //     <key, value>
    private final KafkaTemplate<String, String> alertTemplate;

    public void alertMessage(String message) {

        System.out.println("test_controller");

        this.alertTemplate.send(TOPIC, message);

        //periodic function; invoking every 5 sec



    }


}
