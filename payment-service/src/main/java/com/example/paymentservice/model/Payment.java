package com.example.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Long senderCardId;

    private Long userId;

    private Long recipientCardId;

    private double sum;

    @Column(unique = true)
    private String paymentId;
}

