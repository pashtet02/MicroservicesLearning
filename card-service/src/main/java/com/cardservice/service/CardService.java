package com.cardservice.service;

import com.cardservice.dto.CardDto;

import java.util.List;

public interface CardService {
    CardDto createCard(CardDto cardDto);

    List<CardDto> getAllCards();

    CardDto getCard(Long cardId);

    List<CardDto> findByUserId(Long userId);

    CardDto updateCard(Long cardId, CardDto cardDto);

    void deleteCard(Long cardId);
}
