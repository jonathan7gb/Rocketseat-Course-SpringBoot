package com.weg.rocketseatcourse.application.dto.task;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskResponseDTO (UUID id,
                               String title,
                               String description,
                               LocalDateTime startAt,
                               LocalDateTime endAt,
                               String priority,
                               UUID user_id,
                               LocalDateTime createdAt
                               ){
}
