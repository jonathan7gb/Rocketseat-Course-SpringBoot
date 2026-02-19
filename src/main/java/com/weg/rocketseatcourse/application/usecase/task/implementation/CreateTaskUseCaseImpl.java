package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.dto.task.TaskRequestDTO;
import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.exceptions.TaskCantBeNullException;
import com.weg.rocketseatcourse.application.exceptions.UserNotFoundException;
import com.weg.rocketseatcourse.application.mapper.TaskMapper;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.CreateTaskUseCase;
import com.weg.rocketseatcourse.domain.entity.Task;
import com.weg.rocketseatcourse.domain.entity.User;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import com.weg.rocketseatcourse.infra.persistence.TaskRepositoryImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;


    public CreateTaskUseCaseImpl(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        User userFound = userRepository.findById(taskRequestDTO.user_id())
                .orElseThrow(() -> new UserNotFoundException("User with this Id not found!"));

        Task task = taskMapper.toEntity(taskRequestDTO);
        task.setUser(userFound);

        return taskMapper.toDto(taskRepository.save(task));
    }

}
