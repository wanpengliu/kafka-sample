package com.wp.kafkasample.services;

import com.wp.kafkasample.model.PaymentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendPaymentService {

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public void sendPaymentEvent(String key, PaymentEvent paymentEvent) {

        kafkaTemplate.send("payment", key, paymentEvent);
    }
}
