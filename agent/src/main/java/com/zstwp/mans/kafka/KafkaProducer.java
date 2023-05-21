package com.zstwp.mans.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaProducer {

    private static final String TOPIC = "mans_topic";

//     <key, value>
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void writeMessage(String message) {
        this.kafkaTemplate.send(TOPIC, message);
    }
}
