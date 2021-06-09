package com.example.userservice.dto;

import com.example.userservice.model.enums.Role;
import com.example.userservice.validation.FieldsValueMatch;
import com.example.userservice.validation.UniqueEmailConstraint;
import com.example.userservice.validation.UniqueUsernameConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "repeatPassword",
                message = "{password.mustmatch}"
        )
})
public class UserDto {
    private long id;

    @NotNull(message = "{username.notempty}")
    @NotBlank(message = "username cannot be empty")
    @UniqueUsernameConstraint
    private String username;

    @NotEmpty(message = "{email.notempty}")
    @NotNull
    @Email(message = "{email.notvalid}")
    @UniqueEmailConstraint
    private String email;

    private String password;
    private String repeatPassword;

    private Set<Role> roles;

    @NotBlank( message = "phone number cannot be empty")
    private String phoneNumber;

    private String firstName;
    private String lastName;
    private boolean active;

    public UserDto(String username, String email, String password, String repeatPassword, Set<Role> roles, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
