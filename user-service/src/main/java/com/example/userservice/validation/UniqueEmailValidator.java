package com.example.userservice.validation;

import com.example.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailConstraint, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(UniqueEmailConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userRepository.existsByEmail(email);
    }
}