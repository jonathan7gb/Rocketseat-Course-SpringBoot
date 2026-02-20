package com.weg.rocketseatcourse.presentation.controller;

import com.weg.rocketseatcourse.application.dto.task.TaskRequestDTO;
import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.CreateTaskUseCase;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.DeleteTaskUseCase;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.FindAllTasksUseCase;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.UpdateTaskUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<TaskResponseDTO> saveTask(@RequestBody TaskRequestDTO taskRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(createTaskUseCase.createTask(taskRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAllTasks(){
        return ResponseEntity.ok().body(findAllTasksUseCase.findAllTasks());
    }
}
