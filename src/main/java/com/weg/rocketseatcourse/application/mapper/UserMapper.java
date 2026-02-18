package com.weg.rocketseatcourse.application.mapper;

import com.weg.rocketseatcourse.application.dto.user.UserRequestDTO;
import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;
import com.weg.rocketseatcourse.domain.entity.User;

public class UserMapper {

    public User toEntity(UserRequestDTO userRequestDTO){
        return new User(userRequestDTO.name(), userRequestDTO.email(), userRequestDTO.password());
    }

    public UserResponseDTO toDto(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getCreatedAt());
    }
}
