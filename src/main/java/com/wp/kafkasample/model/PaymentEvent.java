package com.wp.kafkasample.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(name ="cardPaymentEvent", value = CardPaymentEvent.class),
        @JsonSubTypes.Type(name ="onlinePaymentEvent", value = OnlinePaymentEvent.class)
        })
public interface PaymentEvent {

    Double getPrice();
}
