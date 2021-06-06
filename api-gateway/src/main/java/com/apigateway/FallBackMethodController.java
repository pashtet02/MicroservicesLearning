package com.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        return "User service is taking longer than expected, please, try again later";
    }

    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBackMethod() {
        return "Payment service is taking longer than expected, please, try again later";
    }

    @GetMapping("/cardServiceFallBack")
    public String cardServiceFallBackMethod() {
        return "This shit doesn`t work in an appropriate way (cards)";
    }

    @GetMapping("/notificationsServiceFallBack")
    public String notificationsServiceFallBackMethod() {
        return "This shit doesn`t work in an appropriate way (notifications)";
    }
}
