package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.dto.task.TaskRequestDTO;
import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.exceptions.TaskNotFoundException;
import com.weg.rocketseatcourse.application.exceptions.UserNotFoundException;
import com.weg.rocketseatcourse.application.mapper.TaskMapper;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.UpdateTaskUseCase;
import com.weg.rocketseatcourse.domain.entity.Task;
import com.weg.rocketseatcourse.domain.entity.User;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import com.weg.rocketseatcourse.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public UpdateTaskUseCaseImpl(TaskRepository taskRepository, UserRepository userRepository, TaskMapper taskMapper){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponseDTO updateTask(TaskRequestDTO taskRequestDTO, UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with this Id not found;"));

        User userFound = userRepository.findById(taskRequestDTO.user_id())
                .orElseThrow(() -> new UserNotFoundException("User with this Id not found!"));

        task.setTitle(taskRequestDTO.title());
        task.setDescription(taskRequestDTO.description());
        task.setPriority(taskRequestDTO.priority());
        task.setUser(userFound);

        return taskMapper.toDto(taskRepository.save(task));
    }
}
