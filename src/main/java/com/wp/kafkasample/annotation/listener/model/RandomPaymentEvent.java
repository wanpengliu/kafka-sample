package com.wp.kafkasample.annotation.listener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class RandomPaymentEvent implements PaymentEvent {


    private final String name = "Random";

    @Override
    public Payment getPayment() {
        return null;
    }
}
