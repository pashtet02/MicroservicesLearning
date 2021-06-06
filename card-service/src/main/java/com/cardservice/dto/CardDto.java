package com.cardservice.dto;

import com.cardservice.validation.UniqueCardIdConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDto {

    @JsonIgnore
    private Long id;

    @NotNull
    @Positive
    private Long userId;

    private String userFirstName;

    private String userSecondName;

    private Date expirationDate;

    @Min(100)
    @Max(999)
    private String cvv2Code;

    @UniqueCardIdConstraint
    @NotNull
    @Min(value = 100000, message = "card id should be between 100000 ")
    @Max(value = 999999, message = "card id should be between 100000 ")
    private Long cardId;

    private double balance;

    private boolean isCreditCard;

    @Min(value = 1000, message = "should be between 1000 and 9999")
    @Max(value = 9999, message = "should be between 1111 and 9999")
    private String password;
}
