package com.zstwp.mans.dto;

import com.zstwp.mans.database.entities.Specialization;
import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.database.entities.UserRole;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record UserDto(
        String firstName,
        String lastName,
        String phoneNumber,
        @NotBlank @Email String email,
        @NotBlank UserRole role,
        Set<Specialization> specializations) {

    public UserDto(String firstName, String lastName, String email, UserRole role) {
        this(firstName, lastName, null, email, role, null);
    }

    public UserDto(User user) {
        this(user.getFirstName(), user.getLastName(), user.getPhoneNumber(),
                user.getEmail(), user.getRole(), user.getSpecializations());
    }
}
