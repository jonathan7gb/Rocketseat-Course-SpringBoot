package com.weg.rocketseatcourse.application.usecase.user.interfaces;

import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;

import java.util.List;

public interface FindAllUsersUseCase {

    List<UserResponseDTO> findAllUsers();
}
