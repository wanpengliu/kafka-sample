package com.wp.kafkasample.transaction.listener.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class MediumPackage extends DeliveryPackage {

    private double weight;

    public MediumPackage(String receiver, double cost, double weight) {
        super(receiver, cost);
        this.weight = weight;
    }
}
