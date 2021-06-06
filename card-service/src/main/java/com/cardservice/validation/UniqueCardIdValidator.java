package com.cardservice.validation;

import com.cardservice.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@AllArgsConstructor
public class UniqueCardIdValidator implements ConstraintValidator<UniqueCardIdConstraint, Long> {

    private final CardRepository cardRepository;

    @Override
    public void initialize(UniqueCardIdConstraint constraintAnnotation) {
        //hello
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return !cardRepository.existsByCardId(value);
    }
}
