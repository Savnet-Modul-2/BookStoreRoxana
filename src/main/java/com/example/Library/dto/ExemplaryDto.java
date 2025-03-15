package com.example.Library.dto;

import com.example.Library.dto.validation.BasicValidation;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExemplaryDto {
    private Long id;

    @NotNull(groups = BasicValidation.class)
    private String publisher;

    @NotNull(groups = BasicValidation.class)
    private Integer maxReservationDays;
}
