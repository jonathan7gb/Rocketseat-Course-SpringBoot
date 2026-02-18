package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.dto.task.TaskRequestDTO;
import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.CreateTaskUseCase;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import com.weg.rocketseatcourse.infra.persistence.TaskRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepository taskRepository;

    public CreateTaskUseCaseImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {
        return null;
    }

}
