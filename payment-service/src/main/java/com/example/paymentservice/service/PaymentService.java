package com.example.paymentservice.service;

import com.example.paymentservice.dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    PaymentDto savePayment(PaymentDto payment);

    List<PaymentDto> findByUserId(Long userId);

    List<PaymentDto> findBySenderCardId(Long recipientCardId);
}
