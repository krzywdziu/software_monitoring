package com.zstwp.mans.kafka;

import com.zstwp.mans.dto.AlertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KafkaController {

   /* testing only */
    private final KafkaProducer producer;

    @PostMapping("/publish")
    public ResponseEntity<String> writeJsonToTopic(@RequestBody AlertDto alert) {
        producer.sendMessage(alert);
        return ResponseEntity.ok("Json message sent to kafka topic");
    }
}

//{
//        "description": "New Alert",
//        "status": "UNASSIGNED",
//        "boxIp": "10.0.0.1",
//        "severity": "NOTICE",
//        "timestamp": "2019-01-21T05:47:08.644"
//        }