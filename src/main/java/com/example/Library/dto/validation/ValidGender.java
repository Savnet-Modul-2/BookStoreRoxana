package com.example.Library.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {GenderValidator.class})
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface ValidGender {
    String message() default "Gender must be 'Masculin' or 'Feminin'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
