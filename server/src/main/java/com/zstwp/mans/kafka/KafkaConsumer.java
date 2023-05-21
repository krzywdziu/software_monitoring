package com.zstwp.mans.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "mans_topic", groupId = "mans_group_id")
    public void getMessage(String message) {


        System.out.println("Received message: " + message);
    }
}
