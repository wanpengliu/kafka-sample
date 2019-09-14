package com.wp.kafkasample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class CardPayment extends Payment {

    private String location;

    public CardPayment(String merchant, Double amount, String location) {
        super(merchant, amount);
        this.location = location;
    }
}
