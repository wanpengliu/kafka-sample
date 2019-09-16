package com.wp.kafkasample.annotation.listener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Wither;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class OnlinePaymentEvent implements PaymentEvent {

    private OnlinePayment onlinePayment;

    @Override
    public Payment getPayment() {
        return onlinePayment;
    }
}
