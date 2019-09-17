package com.wp.kafkasample.dynamic.bean.listener.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class SmallPackage extends DeliveryPackage {

    private boolean fitLetterBox;

    public SmallPackage(String receiver, double cost, boolean fitLetterBox) {
        super(receiver, cost);
        this.fitLetterBox = fitLetterBox;
    }
}
