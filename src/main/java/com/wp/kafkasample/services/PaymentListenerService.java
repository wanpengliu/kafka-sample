package com.wp.kafkasample.services;

import com.wp.kafkasample.model.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentListenerService {

    @KafkaListener(topics="payment", groupId = "pay")
    public void receive(@Payload PaymentEvent paymentEvent){

        log.info("receive message payment={}", paymentEvent.toString());
    }
}
