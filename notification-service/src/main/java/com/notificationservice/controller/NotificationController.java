package com.notificationservice.controller;

import com.notificationservice.NotificationService;
import com.notificationservice.SmsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/sms")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> sendSms(@Valid @RequestBody SmsRequest smsRequest){
        notificationService.sendSms(smsRequest);
        return new ResponseEntity<>(smsRequest.getMessage(), HttpStatus.CREATED);
    }

}
