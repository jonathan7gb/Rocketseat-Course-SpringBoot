package com.weg.rocketseatcourse.application.usecase.user.implementation;

import com.weg.rocketseatcourse.application.usecase.user.interfaces.FindUserByIdUseCase;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

    private final UserRepository userRepository;

    public FindUserByIdUseCaseImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
