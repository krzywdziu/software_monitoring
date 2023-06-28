package com.zstwp.mans.domain.dto;

import com.zstwp.mans.domain.database.entities.Specialization;
import com.zstwp.mans.domain.database.entities.User;
import com.zstwp.mans.domain.database.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    long id;
    String firstName;
    String lastName;
    String phoneNumber;
    @NotBlank @Email String email;
    @NotBlank UserRole role;
    Set<SpecializationDto> specializations;
}