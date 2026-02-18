package com.weg.rocketseatcourse.application.usecase.user.interfaces;

import com.weg.rocketseatcourse.application.dto.user.UserRequestDTO;
import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;

public interface CreateUserUseCase {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
}
