package com.wp.kafkasample.rest;

import com.wp.kafkasample.model.CardPaymentEvent;
import com.wp.kafkasample.model.OnlinePaymentEvent;
import com.wp.kafkasample.services.SendPaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@AllArgsConstructor
@Slf4j
public class InboundController {

    private SendPaymentService sendPaymentService;
    private static AtomicInteger integer = new AtomicInteger();

    @GetMapping("/cardPayment")
    public String sendCardPayment() {
        integer.addAndGet(1);

        CardPaymentEvent cardPaymentEvent = new CardPaymentEvent().withPrice(35.0);
        sendPaymentService.sendPaymentEvent(String.valueOf(integer), cardPaymentEvent);
        return "sent card payment event";
    }


    @GetMapping("/onlinePayment")
    public String sendOnlinePayment() {

        integer.addAndGet(1);
        OnlinePaymentEvent onlinePaymentEvent = new OnlinePaymentEvent().withPrice(3.5);
        sendPaymentService.sendPaymentEvent(String.valueOf(integer), onlinePaymentEvent);

        return "sent online payment event";
    }
}
