package com.zstwp.mans.kafka;

import com.zstwp.mans.dto.AlertDto;
import com.zstwp.mans.services.AlertHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaConsumerImpl {

    private final AlertHandler alertHandler;

    @KafkaListener(topics = "mans_topic", groupId = "mans_group_id")
    public void getMessage(AlertDto alertDto) {

        System.out.println("Alert received!");

        alertHandler.handleNewAlert(alertDto);
    }
}
