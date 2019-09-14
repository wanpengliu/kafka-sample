package com.wp.kafkasample.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;
import lombok.experimental.Wither;

@Data
@ToString
@JsonTypeName("onlinePaymentEvent")
@AllArgsConstructor
@NoArgsConstructor
@Wither
public class OnlinePaymentEvent implements PaymentEvent {

    private Double price ;

}
