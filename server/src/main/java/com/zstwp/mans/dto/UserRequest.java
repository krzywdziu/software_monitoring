package com.zstwp.mans.dto;

import com.zstwp.mans.database.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private UserRole role;


}
