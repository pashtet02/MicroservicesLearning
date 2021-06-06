package com.example.userservice.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsernameConstraint {
    String message() default "Such username already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}