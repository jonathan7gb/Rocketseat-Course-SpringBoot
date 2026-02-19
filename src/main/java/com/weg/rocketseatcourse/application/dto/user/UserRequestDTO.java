package com.weg.rocketseatcourse.application.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO (
        @NotBlank(message = "Name can't be blank")
        String name,

        @Email(message = "Invalid E-mail")
        @NotBlank(message = "E-mail can't be blank")
        String email,

        @NotBlank(message = "Password can't be blank")
        String password
) {
}
