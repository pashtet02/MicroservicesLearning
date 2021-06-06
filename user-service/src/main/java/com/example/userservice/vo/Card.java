package com.example.userservice.vo;

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

    private int cvv2Code;
    private Long cardId;

    private Long balance;

    private boolean isCreditCard;

    private String password;
}
