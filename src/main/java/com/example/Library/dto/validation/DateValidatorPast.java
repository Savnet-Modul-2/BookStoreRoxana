package com.example.Library.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateValidatorPast implements ConstraintValidator<DateNotInThePast, LocalDate> {
    @Override
    public void initialize(DateNotInThePast dateNotInThePast) {
    }

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        return !date.isBefore(LocalDate.now());
    }
}
