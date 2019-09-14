package com.wp.kafkasample.services.handler;

import com.wp.kafkasample.model.RandomPaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RandomPaymentEventHandler extends AbstractEventHandler<RandomPaymentEvent> {

    @Override
    void print() {

        log.info("random payment event handler is triggered");
    }
}
