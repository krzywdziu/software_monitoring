package com.zstwp.mans.kafka;

import com.zstwp.mans.services.AlertHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {

    private final AlertHandler alertHandler;

    @KafkaListener(topics = "mans_topic", groupId = "mans_group_id")
    public void getMessage(String message) {

//        System.out.println("Received message: " + message);
        System.out.println("Received message");

        alertHandler.handleNewAlert(message);
    }
}
