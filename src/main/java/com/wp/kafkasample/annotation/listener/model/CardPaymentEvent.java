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
public class CardPaymentEvent implements PaymentEvent {
    private CardPayment cardPayment;

    @Override
    public Payment getPayment() {
        return cardPayment;
    }

}
