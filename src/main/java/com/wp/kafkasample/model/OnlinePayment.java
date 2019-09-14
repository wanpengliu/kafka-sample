package com.wp.kafkasample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class OnlinePayment extends Payment {

    private String website;
}
