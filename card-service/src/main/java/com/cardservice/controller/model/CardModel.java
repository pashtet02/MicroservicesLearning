package com.cardservice.controller.model;

import com.cardservice.dto.CardDto;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CardModel extends RepresentationModel<CardModel> {

    @JsonUnwrapped
    private CardDto cardDto;

}
