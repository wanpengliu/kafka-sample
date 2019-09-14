package com.wp.kafkasample.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Wither;

@Data
@ToString
@JsonTypeName("cardPaymentEvent")
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class CardPaymentEvent implements PaymentEvent {
    private Double price;


//    private CardPayment cardPayment;
//
//    @Override
//    public Payment getPayment() {
//        return cardPayment;
//    }
}
