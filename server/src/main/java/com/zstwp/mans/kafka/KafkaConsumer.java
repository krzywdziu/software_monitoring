package com.zstwp.mans.kafka;

import org.springframework.kafka.annotation.KafkaListener;

public interface KafkaConsumer {
    @KafkaListener(topics = "mans_topic", groupId = "mans_group_id")
    void getMessage(String message);
}
