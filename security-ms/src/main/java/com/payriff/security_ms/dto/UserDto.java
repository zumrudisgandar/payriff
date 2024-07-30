package com.payriff.security_ms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Enter username")
    private String username;

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 5, message = "Password length must be at least 5")
    private String password;
}