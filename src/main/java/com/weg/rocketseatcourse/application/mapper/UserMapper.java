package com.weg.rocketseatcourse.application.mapper;

import com.weg.rocketseatcourse.application.dto.user.UserRequestDTO;
import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;
import com.weg.rocketseatcourse.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO userRequestDTO){
        return new User(userRequestDTO.name(), userRequestDTO.email(), userRequestDTO.password());
    }

    public UserResponseDTO toDto(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
    }

    public List<UserResponseDTO> listEntityToDto(List<User> users){
        List<UserResponseDTO> responseDTOS = new ArrayList<>();

        for(User u: users){
            responseDTOS.add(toDto(u));
        }

        return responseDTOS;
    }

}
