package com.example.Library.dto;

import com.example.Library.dto.validation.AdvancedValidation;
import com.example.Library.dto.validation.BasicValidation;
import com.example.Library.dto.validation.DateNotInThePast;
import com.example.Library.dto.validation.ValidDate;
import com.example.Library.entities.StatusReservation;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ValidDate(groups = AdvancedValidation.class)
public class ReservationSearchDto {
    @NotNull(groups = BasicValidation.class)
    @DateNotInThePast(groups = AdvancedValidation.class)
    private LocalDate startDate;

    @NotNull(groups = BasicValidation.class)
    @DateNotInThePast(groups = AdvancedValidation.class)
    private LocalDate endDate;

    private Integer page;
    private Integer size;
    private List<StatusReservation> statuses;
}
