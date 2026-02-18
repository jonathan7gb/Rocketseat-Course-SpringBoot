package com.weg.rocketseatcourse.application.exceptions.exceptionconfig;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp,
                            int status,
                            String message) {
}
