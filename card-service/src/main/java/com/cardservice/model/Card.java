package com.cardservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String userFirstName;

    private String userSecondName;

    private Date expirationDate;

    private String cvv2Code;

    @Column(unique = true)
    private Long cardId;

    private double balance;

    private boolean isCreditCard;

    private String password;
}
