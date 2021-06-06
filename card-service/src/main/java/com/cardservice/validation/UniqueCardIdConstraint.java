package com.cardservice.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueCardIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCardIdConstraint {
    String message() default "Such card already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}