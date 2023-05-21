package com.zstwp.mans.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

}
