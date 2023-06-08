package com.zstwp.mans.kafka;

import com.zstwp.mans.dto.AlertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaProducer {

    protected static final String TOPIC = "mans_topic";

//     <key, value>
    private final KafkaTemplate<String, AlertDto> kafkaTemplate;

    public void sendMessage(AlertDto alert) {
        Message<AlertDto> message = MessageBuilder
                .withPayload(alert)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();

        kafkaTemplate.send(message);
    }
}
