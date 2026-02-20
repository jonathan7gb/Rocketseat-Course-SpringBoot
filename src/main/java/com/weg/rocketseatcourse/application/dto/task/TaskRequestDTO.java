package com.weg.rocketseatcourse.application.dto.task;

import com.weg.rocketseatcourse.domain.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.UUID;

public record TaskRequestDTO (
        @NotBlank(message = "Title can't be blank")
        @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
        String title,

        @NotBlank(message = "Description can't be blank")
        @Size(min = 5, max = 255, message = "Title must be between 5 and 255 characters")
        String description,

        @NotBlank(message = "Priority can't be blank")
        TaskPriority priority,

        @NotNull(message = "User can't be null")
        UUID user_id
){
}
