package com.wp.kafkasample.transaction.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InboundController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/test")
    public void endpoint(@RequestParam(name = "msg") String msg) {

        log.info("send message to topic-1 with value={}", msg);
        kafkaTemplate.send("topic-1", "msg-key", msg);
    }
}
