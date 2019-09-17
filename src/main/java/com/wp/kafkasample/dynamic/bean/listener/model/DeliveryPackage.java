package com.wp.kafkasample.dynamic.bean.listener.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeliveryPackage {

    private String receiver;
    private double cost;
}
