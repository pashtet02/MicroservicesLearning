package com.example.userservice.validation;

import com.example.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsernameConstraint, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(UniqueUsernameConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userRepository.existsByUsername(username);
    }
}
