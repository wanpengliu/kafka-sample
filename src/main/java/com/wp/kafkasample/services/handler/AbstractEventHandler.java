package com.wp.kafkasample.services.handler;

import com.wp.kafkasample.model.PaymentEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractEventHandler<T extends PaymentEvent> implements EventHandler<T> {

    @Override
    public void handle(PaymentEvent paymentEvent) {

        log.info("abstract class got payment event={}", paymentEvent);
        print();
    }

    abstract void print();
}
