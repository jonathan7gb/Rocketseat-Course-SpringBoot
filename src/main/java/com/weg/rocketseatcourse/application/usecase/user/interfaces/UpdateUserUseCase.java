package com.weg.rocketseatcourse.application.usecase.user.interfaces;

import com.weg.rocketseatcourse.application.dto.user.UserRequestDTO;
import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;

import java.util.UUID;

public interface UpdateUserUseCase {

    UserResponseDTO updateUser(UserRequestDTO userRequestDTO, UUID id);
}
