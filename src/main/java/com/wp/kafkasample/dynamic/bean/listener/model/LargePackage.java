package com.wp.kafkasample.dynamic.bean.listener.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class LargePackage extends DeliveryPackage {

    private String transport;

    public LargePackage(String receiver, double cost, String transport) {
        super(receiver, cost);
        this.transport = transport;
    }
}
