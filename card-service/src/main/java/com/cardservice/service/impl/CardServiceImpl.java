package com.cardservice.service.impl;

import com.cardservice.dto.CardDto;
import com.cardservice.exception.CardNotFoundException;
import com.cardservice.mapper.CardMapper;
import com.cardservice.model.Card;
import com.cardservice.repository.CardRepository;
import com.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public CardDto createCard(CardDto cardDto) {
        var card = CardMapper.INSTANCE.toCard(cardDto);
        card.setId(0L);
        String encryptedPass = passwordEncoder.encode(cardDto.getPassword());
        card.setPassword(encryptedPass);
        String encryptedCvv = passwordEncoder
                .encode(String.valueOf(card.getCvv2Code()));
       /* String encryptedExpirationDate = passwordEncoder
                .encode(String.valueOf(card.getCvv2Code()));*/
        card.setCvv2Code(encryptedCvv);
        card = cardRepository.save(card);
        log.debug("createCard() from {}", card);
        return CardMapper.INSTANCE.toCardDto(card);
    }

    @Override
    public List<CardDto> getAllCards() {
        List<CardDto> cardDtoList = new ArrayList<>();
        for (Card card : cardRepository.findAll()) {
            cardDtoList.add(CardMapper.INSTANCE.toCardDto(card));
        }
        log.info("getAllUsers, numberOf users: {}", cardDtoList.size());

        cardDtoList = cardRepository.findAll().stream().map(CardMapper.INSTANCE::toCardDto)
                .collect(Collectors.toList());

        return cardDtoList;
    }

    @Override
    public CardDto getCard(Long cardId) {
        var card = cardRepository.findByCardId(cardId)
                .orElseThrow(CardNotFoundException::new);
        log.debug("getCard() by cardId {}", cardId);
        return CardMapper.INSTANCE.toCardDto(card);
    }

    @Override
    public List<CardDto> findByUserId(Long userId) {
        return cardRepository.findByUserId(userId)
                .stream()
                .map(CardMapper.INSTANCE::toCardDto)
                .collect(Collectors.toList());
    }

    @Override
    public CardDto updateCard(Long cardId, CardDto cardDto) {
        var card = CardMapper.INSTANCE.toCard(cardDto);
        log.info("UPDATING CARD, {}", card);
        if (!cardRepository.existsByCardId(cardId)) {
            throw new CardNotFoundException();
        }
        Optional<Card> cardFromDB = cardRepository.findByCardId(cardId);
        if (cardFromDB.isPresent()) {
            card.setId(cardFromDB.get().getId());
        }
        card = cardRepository.save(card);
        log.debug("updateCard() by cardId {}, from dto: {}", cardId, cardDto);
        return CardMapper.INSTANCE.toCardDto(card);
    }

    @Override
    public void deleteCard(Long cardId) {
        log.debug("deleteCard() by cardId {}", cardId);
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(CardNotFoundException::new);
        cardRepository.delete(card);
    }
}
