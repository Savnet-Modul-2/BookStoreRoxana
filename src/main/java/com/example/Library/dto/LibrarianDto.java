package com.example.Library.dto;

import com.example.Library.dto.validation.AdvancedValidation;
import com.example.Library.dto.validation.BasicValidation;
import com.example.Library.dto.validation.ValidEmail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LibrarianDto {
    @NotNull(groups = BasicValidation.class)
    private Long id;

    @NotNull(groups = BasicValidation.class)
    private String name;

    @NotNull(groups = BasicValidation.class)
    @ValidEmail(groups = AdvancedValidation.class)
    private String email;

    @NotNull(groups = BasicValidation.class)
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private Boolean verifiedAccount = false;
    private String verificationCode;
    private LocalDateTime verificationCodeExpiration;
    private LibraryDto library;
}
