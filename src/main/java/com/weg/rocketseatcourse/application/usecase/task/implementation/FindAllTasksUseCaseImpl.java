package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.FindAllTasksUseCase;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllTasksUseCaseImpl implements FindAllTasksUseCase {

    private final TaskRepository taskRepository;

    public FindAllTasksUseCaseImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponseDTO> findAllTasks() {
        return List.of();
    }
}
