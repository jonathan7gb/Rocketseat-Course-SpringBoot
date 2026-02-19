package com.weg.rocketseatcourse.application.usecase.user.implementation;

import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;
import com.weg.rocketseatcourse.application.exceptions.UserNotFoundException;
import com.weg.rocketseatcourse.application.mapper.UserMapper;
import com.weg.rocketseatcourse.application.usecase.user.interfaces.FindAllUsersUseCase;
import com.weg.rocketseatcourse.domain.entity.User;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Component
public class FindAllUsersUseCaseImpl implements FindAllUsersUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public FindAllUsersUseCaseImpl (UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDTO> findAllUsers() {
        List<User> usersList = userRepository.findAllByOrderByNameAsc();
        if(usersList.isEmpty()){
            throw new UserNotFoundException("No users found!");
        }
        return userMapper.listEntityToDto(usersList);
    }
}
