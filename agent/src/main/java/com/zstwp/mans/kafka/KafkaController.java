package com.zstwp.mans.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KafkaController {

    private final KafkaProducer producer;

    @PostMapping("/publish")
    public void writeMessageToTopic(@RequestParam("message") String message) {
        this.producer.writeMessage(message);
    }
}
