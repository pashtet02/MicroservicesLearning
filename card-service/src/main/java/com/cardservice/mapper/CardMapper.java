package com.cardservice.mapper;

import com.cardservice.dto.CardDto;
import com.cardservice.model.Card;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card toCard(CardDto cardDto);

    @InheritInverseConfiguration
    CardDto toCardDto(Card card);
}
