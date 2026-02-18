package com.weg.rocketseatcourse.presentation.controller;

import com.weg.rocketseatcourse.application.usecase.task.interfaces.CreateTaskUseCase;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.DeleteTaskUseCase;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.FindAllTasksUseCase;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.UpdateTaskUseCase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final FindAllTasksUseCase findAllTasksUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    public TaskController(
            CreateTaskUseCase createTaskUseCase,
            FindAllTasksUseCase findAllTasksUseCase,
            UpdateTaskUseCase updateTaskUseCase,
            DeleteTaskUseCase deleteTaskUseCase
    ){
        this.createTaskUseCase = createTaskUseCase;
        this.findAllTasksUseCase = findAllTasksUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
    }
}
