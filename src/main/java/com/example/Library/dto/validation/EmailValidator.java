package com.example.Library.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    public void initialize(ValidEmail validEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email.toLowerCase().endsWith("@gmail.com");
    }
}
