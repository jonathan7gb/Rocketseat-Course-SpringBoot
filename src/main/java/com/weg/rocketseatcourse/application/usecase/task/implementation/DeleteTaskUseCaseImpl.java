package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.usecase.task.interfaces.DeleteTaskUseCase;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {

    private final TaskRepository taskRepository;

    public DeleteTaskUseCaseImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
}
