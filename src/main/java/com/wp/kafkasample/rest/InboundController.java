package com.wp.kafkasample.rest;

import com.wp.kafkasample.model.*;
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

        CardPayment cardPayment = new CardPayment("shop", 3.5, "london");
        CardPaymentEvent cardPaymentEvent = new CardPaymentEvent(cardPayment);
        sendPaymentService.sendPaymentEvent(String.valueOf(integer), cardPaymentEvent);
        return "sent card payment event";
    }

    @GetMapping("/onlinePayment")
    public String sendOnlinePayment() {

        integer.addAndGet(1);

        OnlinePayment onlinePayment = new OnlinePayment("online", 35.0, "google.com");
        OnlinePaymentEvent onlinePaymentEvent = new OnlinePaymentEvent(onlinePayment);
        sendPaymentService.sendPaymentEvent(String.valueOf(integer), onlinePaymentEvent);

        return "sent online payment event";
    }

    @GetMapping("/randomPayment")
    public String sendRandomPayment() {

        integer.addAndGet(1);

        RandomPaymentEvent randomPaymentEvent = new RandomPaymentEvent();

        sendPaymentService.sendPaymentEvent(String.valueOf(integer), randomPaymentEvent);

        return "sent random payment event";
    }
}
