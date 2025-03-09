package com.example.Library.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = {DateValidatorPast.class})
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface DateNotInThePast {
    String message() default "The start date and end date must not be in the past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
