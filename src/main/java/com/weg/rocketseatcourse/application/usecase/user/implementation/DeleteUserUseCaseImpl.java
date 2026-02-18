package com.weg.rocketseatcourse.application.usecase.user.implementation;

import com.weg.rocketseatcourse.application.exceptions.UserNotFoundException;
import com.weg.rocketseatcourse.application.usecase.user.interfaces.DeleteUserUseCase;
import com.weg.rocketseatcourse.domain.entity.User;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserUseCaseImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with this Id not found!"));

        userRepository.deleteById(user.getId());
    }
}
