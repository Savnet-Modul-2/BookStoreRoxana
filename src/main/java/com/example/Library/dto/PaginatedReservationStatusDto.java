package com.example.Library.dto;

import com.example.Library.entities.StatusReservation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedReservationStatusDto {
    private StatusReservation statusReservation;
    private Integer page;
    private Integer size;
}
