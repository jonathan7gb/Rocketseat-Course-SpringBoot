package com.weg.rocketseatcourse.application.usecase.user.implementation;

import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;
import com.weg.rocketseatcourse.application.exceptions.UserNotFoundException;
import com.weg.rocketseatcourse.application.mapper.UserMapper;
import com.weg.rocketseatcourse.application.usecase.user.interfaces.FindUserByIdUseCase;
import com.weg.rocketseatcourse.domain.entity.User;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Service
public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public FindUserByIdUseCaseImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDTO findUserByID(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with this Id not found!"));

        return userMapper.toDto(user);
    }


    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDTO> findByName(String name){
        List<User> usersFound = userRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
        if(usersFound.isEmpty()){
            throw new UserNotFoundException("No users found!");
        }
        return userMapper.listEntityToDto(usersFound);
    }
}
