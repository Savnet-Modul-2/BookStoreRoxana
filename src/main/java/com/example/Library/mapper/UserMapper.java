package com.example.Library.mapper;

import com.example.Library.dto.UserDto;
import com.example.Library.entities.User;

public class UserMapper {
    public static User toEntity(UserDto userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setYearOfBirth(userDTO.getYearOfBirth());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(userDTO.getPassword());
        user.setCountry(userDTO.getCountry());
        user.setVerifiedAccount(userDTO.getVerifiedAccount());
        user.setVerificationCode(userDTO.getVerificationCode());
        user.setVerificationCodeExpiration(userDTO.getVerificationCodeExpiration());
        return user;
    }

    public static UserDto toDto(User user) {
        UserDto userDTO = new UserDto();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setYearOfBirth(user.getYearOfBirth());
        userDTO.setGender(user.getGender());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setPassword(user.getPassword());
        userDTO.setCountry(user.getCountry());
        userDTO.setVerifiedAccount(user.getVerifiedAccount());
        userDTO.setVerificationCode(user.getVerificationCode());
        userDTO.setVerificationCodeExpiration(user.getVerificationCodeExpiration());
        return userDTO;
    }
}
