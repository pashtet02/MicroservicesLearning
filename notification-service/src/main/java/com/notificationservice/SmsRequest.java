package com.notificationservice;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@RequiredArgsConstructor
@Getter
@ToString
public class SmsRequest {

    @NotBlank
    private final String phoneNumber;
    @NotBlank
    private final String message;
}
