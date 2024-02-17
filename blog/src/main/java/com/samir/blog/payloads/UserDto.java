package com.samir.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Singular;

@Data
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min 4 characters")
    private String name;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be between min 3 characters and max 10 characters")
    private String password;
    @NotEmpty
    @Size(max = 20)
    private String about;
}

