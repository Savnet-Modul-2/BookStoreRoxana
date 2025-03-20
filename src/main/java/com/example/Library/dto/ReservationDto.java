package com.example.Library.dto;

import com.example.Library.dto.validation.AdvancedValidation;
import com.example.Library.dto.validation.BasicValidation;
import com.example.Library.dto.validation.ValidDate;
import com.example.Library.dto.validation.FutureOrPresentDate;
import com.example.Library.entities.StatusReservation;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ValidDate(groups = AdvancedValidation.class)
public class ReservationDto {
    private Long id;

    @NotNull(groups = BasicValidation.class)
    @FutureOrPresentDate(groups = AdvancedValidation.class)
    private LocalDate startDate;

    @NotNull(groups = BasicValidation.class)
    @FutureOrPresentDate(groups = AdvancedValidation.class)
    private LocalDate endDate;

    private StatusReservation statusReservation;
    private UserDto user;
    private ExemplaryDto exemplary;
}
