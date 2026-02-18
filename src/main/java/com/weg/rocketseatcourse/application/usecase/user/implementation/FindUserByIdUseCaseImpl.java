package com.weg.rocketseatcourse.application.usecase.user.implementation;

import com.weg.rocketseatcourse.application.mapper.UserMapper;
import com.weg.rocketseatcourse.application.usecase.user.interfaces.FindUserByIdUseCase;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public FindUserByIdUseCaseImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
}
