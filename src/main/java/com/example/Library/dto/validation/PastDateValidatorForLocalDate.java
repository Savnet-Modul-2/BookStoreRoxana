package com.example.Library.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PastDateValidatorForLocalDate implements ConstraintValidator<PastDate,LocalDate> {
    @Override
    public void initialize(PastDate pastDate) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return date.isBefore(LocalDate.now());
    }
}
