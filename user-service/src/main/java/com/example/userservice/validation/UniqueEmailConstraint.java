package com.example.userservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueEmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmailConstraint {
    String message() default "Such email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}