package com.example.Library.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PastDateValidator implements ConstraintValidator<DateNotInTheFuture, Integer> {
    @Override
    public void initialize(DateNotInTheFuture dateNotInTheFuture) {
    }

    @Override
    public boolean isValid(Integer yearOfBirth, ConstraintValidatorContext context) {
        int currentYear = LocalDate.now().getYear();
        return yearOfBirth < currentYear;
    }
}
