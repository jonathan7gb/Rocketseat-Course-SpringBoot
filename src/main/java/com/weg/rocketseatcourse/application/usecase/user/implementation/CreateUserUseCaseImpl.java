package com.weg.rocketseatcourse.application.usecase.user.implementation;

import com.weg.rocketseatcourse.application.dto.user.UserRequestDTO;
import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;
import com.weg.rocketseatcourse.application.exceptions.EmailAlreadyExistsException;
import com.weg.rocketseatcourse.application.exceptions.UserCantBeNullException;
import com.weg.rocketseatcourse.application.mapper.UserMapper;
import com.weg.rocketseatcourse.application.usecase.user.interfaces.CreateUserUseCase;
import com.weg.rocketseatcourse.domain.entity.User;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCaseImpl (UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if(userRepository.existsByEmail(userRequestDTO.email())){
            throw new EmailAlreadyExistsException("E-mail already registered!");
        }

        User user = userMapper.toEntity(userRequestDTO);
        user.encryptPassword(passwordEncoder);
        User userSaved = userRepository.save(user);

        return userMapper.toDto(userSaved);
    }

}
