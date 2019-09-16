package com.wp.kafkasample.annotation.listener.services.handler;

import com.wp.kafkasample.annotation.listener.model.CardPaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CardPaymentEventHandler implements EventHandler<CardPaymentEvent> {
    @Override
    public void handle(CardPaymentEvent cardPaymentEvent) {

        log.info("card payment event={} received by handler", cardPaymentEvent);
    }
}
