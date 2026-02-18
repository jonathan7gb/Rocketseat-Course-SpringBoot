package com.weg.rocketseatcourse.application.usecase.user.implementation;

import com.weg.rocketseatcourse.application.dto.user.UserRequestDTO;
import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;
import com.weg.rocketseatcourse.application.exceptions.InvalidEmailException;
import com.weg.rocketseatcourse.application.exceptions.UserNotFoundException;
import com.weg.rocketseatcourse.application.mapper.UserMapper;
import com.weg.rocketseatcourse.application.usecase.user.interfaces.UpdateUserUseCase;
import com.weg.rocketseatcourse.domain.entity.User;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UpdateUserUseCaseImpl (UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with this Id not found!"));

        if(userRepository.existsByEmailAndIdNot(userRequestDTO.email(), id)){
            throw new InvalidEmailException("E-mail already in use!");
        }

        user.setName(userRequestDTO.name());
        user.setEmail(userRequestDTO.email());
        if (userRequestDTO.password() != null && !userRequestDTO.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        }
        return userMapper.toDto(userRepository.save(user));
    }
}
