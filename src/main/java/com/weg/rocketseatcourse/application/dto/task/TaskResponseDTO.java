package com.weg.rocketseatcourse.application.dto.task;

import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;
import com.weg.rocketseatcourse.domain.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDTO (UUID id,
                               String title,
                               String description,
                               LocalDateTime startAt,
                               LocalDateTime endAt,
                               String priority,
                               UserResponseDTO user,
                               LocalDateTime createdAt
                               ){
}
