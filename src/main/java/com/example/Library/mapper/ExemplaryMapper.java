package com.example.Library.mapper;

import com.example.Library.dto.ExemplaryDto;
import com.example.Library.entities.Exemplary;

import java.util.List;
import java.util.stream.Collectors;

public class ExemplaryMapper {
    public static Exemplary toEntity(ExemplaryDto exemplaryDto) {
        Exemplary exemplary = new Exemplary();
        exemplary.setPublisher(exemplaryDto.getPublisher());
        exemplary.setMaxReservationDays(exemplaryDto.getMaxReservationDays());
        return exemplary;
    }

    public static ExemplaryDto toDto(Exemplary exemplary) {
        ExemplaryDto exemplaryDto = new ExemplaryDto();
        exemplaryDto.setId(exemplary.getId());
        exemplaryDto.setPublisher(exemplary.getPublisher());
        exemplaryDto.setMaxReservationDays(exemplary.getMaxReservationDays());
        return exemplaryDto;
    }

    public static List<ExemplaryDto> toDtoList(List<Exemplary> exemplars) {
        return exemplars.stream().map(ExemplaryMapper::toDto).toList();
    }
}
