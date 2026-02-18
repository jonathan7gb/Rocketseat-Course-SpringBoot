package com.weg.rocketseatcourse.application.dto.task;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskRequestDTO (
        @NotEmpty(message = "Title can't be empty")
        @NotNull(message = "Title can't be null")
        String title,

        @NotEmpty(message = "Description can't be empty")
        @NotNull(message = "Description can't be null")
        String description,
        LocalDateTime startAt,
        LocalDateTime endAt,
        String priority,
        UUID user_id
){
}
