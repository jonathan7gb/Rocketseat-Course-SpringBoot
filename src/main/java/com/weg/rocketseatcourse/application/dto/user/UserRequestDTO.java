package com.weg.rocketseatcourse.application.dto.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO (
        @NotNull(message = "Name can't be null")
        @NotEmpty(message = "Name can't be empty")
        String name,

        @Email(message = "Invalid E-mail")
        @NotEmpty(message = "E-mail can't be empty")
        @NotNull(message = "E-mail can't be null")
        String email,

        @NotNull(message = "Password can't be null")
        @NotEmpty(message = "Password can't be empty")
        String password
) {
}
