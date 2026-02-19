package com.weg.rocketseatcourse.application.dto.task;

import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;
import com.weg.rocketseatcourse.domain.entity.User;
import com.weg.rocketseatcourse.domain.enums.TaskPriority;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDTO (UUID id,
                               String title,
                               String description,
                               LocalDateTime startAt,
                               LocalDateTime endAt,
                               TaskPriority priority,
                               UserResponseDTO user,
                               LocalDateTime createdAt
                               ){
}
