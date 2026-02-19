package com.weg.rocketseatcourse.application.dto.task;

import com.weg.rocketseatcourse.domain.enums.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.util.UUID;

public record TaskRequestDTO (
        @NotBlank(message = "Title can't be blank")
        String title,

        @NotBlank(message = "Description can't be blank")
        String description,

        @NotBlank(message = "Priority can't be blank")
        TaskPriority priority,

        @NotNull(message = "User can't be null")
        UUID user_id
){
}
