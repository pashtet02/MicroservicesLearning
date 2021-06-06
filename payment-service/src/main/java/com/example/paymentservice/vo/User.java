package com.example.paymentservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;

    private String username;
    private String email;

    private String password;
    private String firstName;
    private String lastName;
    private Boolean locked;
    private Boolean enabled;
}
