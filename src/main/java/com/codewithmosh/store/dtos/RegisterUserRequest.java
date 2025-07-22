package com.codewithmosh.store.dtos;

import com.codewithmosh.store.validations.Lowercase;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data // this is the combination of getter setter to string and much more
public class RegisterUserRequest {
    @NotBlank(message = "Name is required")
    @Size(message = "Name must be less than 255 characters", max = 255)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be a valid email")
    @Lowercase(message = "Email must be in lowercase")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 25, message = "Password must be between 6 to 25 characters long.")
    private String password;
}
