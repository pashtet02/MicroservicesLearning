package com.example.paymentservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Long id;

    private Long userId;

    private String userFirstName;

    private String userSecondName;

    private Date expirationDate;

    private String cvv2Code;

    private Long cardId;

    private double balance;

    private boolean isCreditCard;

    private String password;
}
