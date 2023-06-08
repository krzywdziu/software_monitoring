package com.zstwp.mans.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class KafkaConsumer {

    private final KafkaAlertHandler kafkaAlertHandler;

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "mans_topic", groupId = "mans_group_id")
    public void getMessage(String message) {

        log.info(String.format("Json message received -> %s ", message));

        try {
            //alert format received from agent (as json)
            KafkaAlertDto kafkaAlertDto = objectMapper.readValue(message, KafkaAlertDto.class);
            kafkaAlertHandler.handleNewAlert(kafkaAlertDto);

        } catch (JsonProcessingException e) {
            e.getMessage();
//            e.printStackTrace();
        }
    }
}
