package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.usecase.task.interfaces.FindAllTasksUseCase;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class FindAllTasksUseCaseImpl implements FindAllTasksUseCase {

    private final TaskRepository taskRepository;

    public FindAllTasksUseCaseImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
}
