package com.wp.kafkasample.annotation.listener.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class OnlinePayment extends Payment {

    private String website;

    public OnlinePayment(String merchant, Double amount, String website) {
        super(merchant, amount);
        this.website = website;
    }
}
