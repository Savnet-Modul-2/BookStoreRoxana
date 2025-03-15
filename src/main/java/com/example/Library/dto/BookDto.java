package com.example.Library.dto;

import com.example.Library.dto.validation.AdvancedValidation;
import com.example.Library.dto.validation.BasicValidation;
import com.example.Library.dto.validation.PastDate;
import com.example.Library.entities.BookCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class BookDto {
    private Long id;

    @NotNull(groups = BasicValidation.class)
    private Integer isbn;

    @NotNull(groups = BasicValidation.class)
    private String title;

    @NotNull(groups = BasicValidation.class)
    private String author;

    @NotNull(groups = BasicValidation.class)
    @PastDate(groups = AdvancedValidation.class)
    private LocalDate appearanceDate;

    @NotNull(groups = BasicValidation.class)
    private Integer nrOfPages;

    private BookCategory bookCategory;

    @NotNull(groups = BasicValidation.class)
    private String language;

    private List<ExemplaryDto> exemplars;
}
