package com.example.paymentservice.service.impl;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.exception.CardNotFoundException;
import com.example.paymentservice.mapper.PaymentMapper;
import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.PaymentService;
import com.example.paymentservice.vo.Card;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    @Override
    public PaymentDto savePayment(PaymentDto paymentDto) {
        var payment = PaymentMapper.INSTANCE.toPayment(paymentDto);
        payment.setId(0);
        payment = paymentRepository.save(payment);
        log.info("save payment {}", payment);
        return PaymentMapper.INSTANCE.toPaymentDto(payment);
    }

    @Override
    public List<PaymentDto> findByUserId(Long userId){
        List<Payment> payments= paymentRepository.getAllByUserId(userId);

        log.info("get all payments by userId: {}", payments);
        return payments.stream()
                .map(PaymentMapper.INSTANCE::toPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDto> findBySenderCardId(Long recipientCardId) {
        var card = restTemplate.getForObject("http://CARDSERVICE/api/v1/cards?cardId="
                + recipientCardId, Card.class);
        if (card == null){
            throw new CardNotFoundException("Card not found!");
        }

        List<Payment> payments= paymentRepository.getAllBySenderCardId(recipientCardId);
        log.info("get all payments by recipientCardId: {}", payments);
        return payments.stream()
                .map(PaymentMapper.INSTANCE::toPaymentDto)
                .collect(Collectors.toList());
    }
}
