package com.wp.kafkasample.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {

    private String merchant;

    private Double amount;

}
