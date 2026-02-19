package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.FindTaskByIdUseCase;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;

import java.util.List;
import java.util.UUID;

public class FindTaskByIdUseCaseImpl implements FindTaskByIdUseCase {

    private TaskRepository taskRepository;

    public FindTaskByIdUseCaseImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDTO findTaskByID(UUID id) {
        return null;
    }

    @Override
    public List<TaskResponseDTO> findByName(String name) {
        return List.of();
    }

    @Override
    public List<TaskResponseDTO> findByUser(UUID user_id) {
        return List.of();
    }
}
