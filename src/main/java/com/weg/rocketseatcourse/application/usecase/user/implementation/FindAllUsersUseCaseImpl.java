package com.weg.rocketseatcourse.application.usecase.user.implementation;

import com.weg.rocketseatcourse.application.usecase.user.interfaces.FindAllUsersUseCase;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class FindAllUsersUseCaseImpl implements FindAllUsersUseCase {

    private final UserRepository userRepository;

    public FindAllUsersUseCaseImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
