package com.example.userservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private long id;

    private String sender;

    private Long userId;

    private String recipient;

    private double sum;

    private String paymentId;
}
