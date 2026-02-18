package com.weg.rocketseatcourse.application.usecase.user.interfaces;

import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;

import java.util.Optional;
import java.util.UUID;

public interface FindUserByIdUseCase {

    UserResponseDTO findUserByID(UUID id);
}
