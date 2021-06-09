package com.example.paymentservice.controller;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payments")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;
    private final RestTemplate restTemplate;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDto savePayment(@Valid @RequestBody PaymentDto payment) {
        log.info("saved payment:  {}", payment);
        return paymentService.savePayment(payment);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDto> getPaymentsByUserId(@RequestParam("userId") Long userId) {
        log.info("get payments:  {}", userId);
        return paymentService.findByUserId(userId);
    }

    @GetMapping("{senderCardId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDto> getPaymentsByRecipientCard(@PathVariable("senderCardId") Long senderCardId) {
        //Long cardId = Long.parseLong(recipientCardId);
        log.info("Get all payments by card id: {}", senderCardId);
        return paymentService.findBySenderCardId(senderCardId);
    }
}
