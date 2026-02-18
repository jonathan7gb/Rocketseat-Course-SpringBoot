package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.usecase.task.interfaces.UpdateTaskUseCase;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {

    private final TaskRepository taskRepository;

    public UpdateTaskUseCaseImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
}
