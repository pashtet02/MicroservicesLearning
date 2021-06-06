package com.cardservice.controller;

import com.cardservice.controller.assembler.CardAssembler;
import com.cardservice.controller.model.CardModel;
import com.cardservice.dto.CardDto;
import com.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
@Slf4j
public class CardController {
    private final CardService cardService;
    private final CardAssembler cardAssembler;

    /*@ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CardDto> getAllCards() {
        return cardService.getAllCards();
    }*/

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public CardDto getCard(@RequestParam("cardId") Long cardId) {
        log.info("getCard() by cardId: {}", cardId);
        return cardService.getCard(cardId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CardModel createCard(@Valid @RequestBody CardDto cardDto) {
        log.info("createCard() cardController: {}", cardDto);
        CardDto card = cardService.createCard(cardDto);
        return cardAssembler.toModel(card);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public List<CardDto> getCardsByUserId(@PathVariable("id") Long userId) {
        log.info("get payments:  {}", userId);
        return cardService.findByUserId(userId);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{cardId}")
    public CardModel updateCard(@PathVariable Long cardId, @RequestBody CardDto cardDto) {
        log.info("Update card by cardId: {} card: {}", cardId, cardDto);
        CardDto card = cardService.updateCard(cardId, cardDto);
        return cardAssembler.toModel(card);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId) {
        log.info("Delete card by cardId: {}", cardId);
        cardService.deleteCard(cardId);
        return ResponseEntity.noContent().build();
    }
}
