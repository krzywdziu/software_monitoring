package com.zstwp.mans.dto;

import com.zstwp.mans.database.entities.Specialization;
import com.zstwp.mans.database.entities.User;
import com.zstwp.mans.database.entities.UserRole;
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
    String firstName;
    String lastName;
    String phoneNumber;
    @NotBlank @Email String email;
    @NotBlank UserRole role;
    Set<Specialization> specializations;

    public UserDto(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.specializations = user.getSpecializations();
    }
}