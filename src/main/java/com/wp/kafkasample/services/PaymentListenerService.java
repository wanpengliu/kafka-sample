package com.wp.kafkasample.services;

import com.wp.kafkasample.model.PaymentEvent;
import com.wp.kafkasample.services.handler.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaymentListenerService {

    @Autowired
    private List<EventHandler> handlers;

    @KafkaListener(topics = "payment", groupId = "pay")
    public void receive(@Payload PaymentEvent paymentEvent) {

        log.info("receive message payment={}", paymentEvent);

        handlers.stream()
                .filter(handler -> paymentEvent.getClass().equals(handler.getEventType()))
                // Eventhandler cannot use ? extends because it is invariant.
                // the specific handler expects a subclass, when a parent class is passed, it's problematic
                //because the filter step already guaranteed the type match, hence this step is safe
                .forEach(handler -> handler.handle(paymentEvent));

    }
}
