package com.notificationservice;

import com.notificationservice.exception.NotValidPhoneNumberException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TwilioSmsSender implements SmsSender {
    private final TwilioConfiguration configuration;
    @Override
    public void sendSms(SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())){
            PhoneNumber from = new PhoneNumber(configuration.getTrialNumber());
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            log.info("send sms {}" , smsRequest);
        } else {
            throw new NotValidPhoneNumberException("Phone number" + smsRequest.getPhoneNumber() + "is not valid");
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber) {

        return true;
    }
}
