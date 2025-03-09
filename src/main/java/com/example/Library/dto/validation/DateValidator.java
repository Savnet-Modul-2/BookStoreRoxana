package com.example.Library.dto.validation;

import com.example.Library.dto.ReservationDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<ValidDate, ReservationDto> {
    @Override
    public void initialize(ValidDate validDate) {
    }

    @Override
    public boolean isValid(ReservationDto reservationDto, ConstraintValidatorContext context) {
        return !reservationDto.getStartDate().isAfter(reservationDto.getEndDate());
    }
}