package com.cardservice.service.impl;

import com.cardservice.dto.CardDto;
import com.cardservice.exception.CardNotFoundException;
import com.cardservice.exception.ServiceException;
import com.cardservice.mapper.CardMapper;
import com.cardservice.model.Card;
import com.cardservice.repository.CardRepository;
import com.cardservice.service.CardService;
import com.cardservice.vo.SmsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private final RestTemplate restTemplate;

    @Override
    public CardDto createCard(CardDto cardDto) {
        var card = CardMapper.INSTANCE.toCard(cardDto);
        card.setId(0L);
        String encryptedPass = passwordEncoder.encode(cardDto.getPassword());
        card.setPassword(encryptedPass);
        String encryptedCvv = passwordEncoder
                .encode(String.valueOf(card.getCvv2Code()));
        card.setCvv2Code(encryptedCvv);

        card = cardRepository.save(card);
        String message = "Your card was created: " + card.getCardId();
        this.sendSms(cardDto.getPhoneNumber(), message);
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
        String encryptedPass = passwordEncoder.encode(cardDto.getPassword());
        card.setPassword(encryptedPass);
        String encryptedCvv = passwordEncoder
                .encode(String.valueOf(card.getCvv2Code()));
        card.setCvv2Code(encryptedCvv);
        card = cardRepository.save(card);

        String message = "Your card info was updated! Current balance: " + card.getBalance();
        this.sendSms(cardDto.getPhoneNumber(), message);
        log.debug("updateCard() by cardId {}, from dto: {}", cardId, cardDto);
        return CardMapper.INSTANCE.toCardDto(card);
    }

    @Override
    public void deleteCard(Long cardId) {
        log.debug("deleteCard() by cardId {}", cardId);
        var card = cardRepository.findByCardId(cardId)
                .orElseThrow(CardNotFoundException::new);
        cardRepository.delete(card);
    }

    private ResponseEntity<SmsRequest> sendSms(String phoneNumber, String message){
        HttpEntity<SmsRequest> request =
                new HttpEntity<>(new SmsRequest(phoneNumber, message));
        return restTemplate.exchange("http://NOTIFICATIONSERVICE/api/v1/sms",
                HttpMethod.POST, request, SmsRequest.class);
    }
}
