package com.example.paymentservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    @JsonIgnore
    private long id;

    @NotNull
    @Min(value = 100000, message = "card id should be between 100000 ")
    @Max(value = 999999, message = "card id should be between 100000 ")
    private Long senderCardId;

    private Long userId;
    @NotNull
    @Min(value = 100000, message = "card id should be between 100000 ")
    @Max(value = 999999, message = "card id should be between 100000 ")
    private Long recipientCardId;

    @Positive
    private double sum;

    private String paymentId;
}
