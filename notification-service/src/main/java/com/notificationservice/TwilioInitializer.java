package com.notificationservice;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TwilioInitializer {

    private final TwilioConfiguration configuration;

    @Autowired
    public TwilioInitializer(TwilioConfiguration configuration) {
        this.configuration = configuration;
        Twilio.init(configuration.getAccountSid(),
                configuration.getAuthToken());
        log.info("Twilio initialized... with account SID {}", configuration.getAccountSid());
    }


}
