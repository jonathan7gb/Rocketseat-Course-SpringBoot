package com.weg.rocketseatcourse.application.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO (UUID id,
                               String name,
                               String email,
                               LocalDateTime createdAt,
                               String role
) {
}
