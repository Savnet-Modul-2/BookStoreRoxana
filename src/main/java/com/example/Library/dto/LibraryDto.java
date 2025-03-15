package com.example.Library.dto;

import com.example.Library.dto.validation.AdvancedValidation;
import com.example.Library.dto.validation.BasicValidation;
import com.example.Library.dto.validation.DigitsOnly;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LibraryDto {
    private Long id;

    @NotNull(groups = BasicValidation.class)
    private String name;

    @NotNull(groups = BasicValidation.class)
    private String address;

    @NotNull(groups = BasicValidation.class)
    @DigitsOnly(groups = AdvancedValidation.class)
    private Integer phoneNumber;

    private List<BookDto> books;
}
