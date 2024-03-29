package com.wp.kafkasample.annotation.listener.services.handler;

import com.wp.kafkasample.annotation.listener.model.OnlinePaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OnlinePaymentEventHandler implements EventHandler<OnlinePaymentEvent> {
    @Override
    public void handle(OnlinePaymentEvent onlinePaymentEvent) {

        log.info("online payment event={} received", onlinePaymentEvent);
    }
}
