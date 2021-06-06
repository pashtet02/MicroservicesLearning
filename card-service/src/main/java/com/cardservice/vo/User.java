package com.cardservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;

    private String password;


    private double fine;
    private String userLocale;
    private String firstName;
    private String lastName;
    private Boolean locked = false;
    private Boolean enabled = true;
}
