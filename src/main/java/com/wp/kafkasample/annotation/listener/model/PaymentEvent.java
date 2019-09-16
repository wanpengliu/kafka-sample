package com.wp.kafkasample.annotation.listener.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface PaymentEvent {

    Payment getPayment();
}
