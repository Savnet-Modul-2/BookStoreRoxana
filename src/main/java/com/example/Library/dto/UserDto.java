package com.example.Library.dto;

import com.example.Library.dto.validation.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private Long id;

    @NotNull(groups = BasicValidation.class)
    private String firstName;

    @NotNull(groups = BasicValidation.class)
    private String lastName;

    @NotNull(groups = BasicValidation.class)
    @DateNotInTheFuture(groups = AdvancedValidation.class)
    private Integer yearOfBirth;

    @NotNull(groups = BasicValidation.class)
    @ValidGender(groups = AdvancedValidation.class)
    private String gender;

    @NotNull(groups = {BasicValidation.class, LoginValidation.class})
    @ValidEmail(groups = AdvancedValidation.class)
    private String email;

    @NotNull(groups = BasicValidation.class)
    @DigitsOnly(groups = AdvancedValidation.class)
    private String phoneNumber;

    @NotNull(groups = {BasicValidation.class, LoginValidation.class})
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(groups = BasicValidation.class)
    private String country;

    private Boolean verifiedAccount = false;
    private String verificationCode;
    private LocalDateTime verificationCodeExpiration;
}
