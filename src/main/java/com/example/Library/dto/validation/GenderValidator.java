package com.example.Library.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<ValidGender, String> {
    public void initialize(ValidGender validGender) {
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext context) {
        return gender.equalsIgnoreCase("Feminin") || gender.equalsIgnoreCase("Masculin");
    }
}
