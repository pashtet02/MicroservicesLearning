package com.notificationservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SmsSender smsSender;

    public void sendSms(SmsRequest smsRequest){
        this.smsSender.sendSms(smsRequest);
    }
}
