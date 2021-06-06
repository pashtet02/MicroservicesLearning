package com.example.paymentservice.mapper;

import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.model.Payment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment toPayment(PaymentDto paymentDro);

    @InheritInverseConfiguration
    PaymentDto toPaymentDto(Payment user);
}
