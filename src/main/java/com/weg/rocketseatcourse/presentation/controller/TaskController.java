package com.weg.rocketseatcourse.presentation.controller;

import com.weg.rocketseatcourse.application.dto.task.TaskRequestDTO;
import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.usecase.task.implementation.FindTaskByIdUseCaseImpl;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.*;
import com.weg.rocketseatcourse.domain.enums.TaskPriority;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final FindAllTasksUseCase findAllTasksUseCase;
    private final FindTaskByIdUseCase findTaskByIdUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;

    public TaskController(
            CreateTaskUseCase createTaskUseCase,
            FindAllTasksUseCase findAllTasksUseCase,
            FindTaskByIdUseCase findTaskByIdUseCase,
            UpdateTaskUseCase updateTaskUseCase,
            DeleteTaskUseCase deleteTaskUseCase
    ){
        this.createTaskUseCase = createTaskUseCase;
        this.findAllTasksUseCase = findAllTasksUseCase;
        this.findTaskByIdUseCase = findTaskByIdUseCase;
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

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findTaskById(@PathVariable UUID id){
        return ResponseEntity.ok().body(findTaskByIdUseCase.findTaskByID(id));
    }

    @GetMapping("/searchbytitle/{title}")
    public ResponseEntity<List<TaskResponseDTO>> findTasksByTitle(@PathVariable String title){
        return ResponseEntity.ok().body(findTaskByIdUseCase.findByTitle(title));
    }

    @GetMapping("/searchbyuser/{user_id}")
    public ResponseEntity<List<TaskResponseDTO>> findTasksByTitle(@PathVariable UUID user_id){
        return ResponseEntity.ok().body(findTaskByIdUseCase.findByUser(user_id));
    }

    @GetMapping("/searchbypriority/{priority}")
    public ResponseEntity<List<TaskResponseDTO>> findTasksByTitle(@PathVariable TaskPriority priority){
        return ResponseEntity.ok().body(findTaskByIdUseCase.findByPriority(priority));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable UUID id, @RequestBody TaskRequestDTO taskRequestDTO){
        return ResponseEntity.ok().body(updateTaskUseCase.updateTask(taskRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable UUID id){
        deleteTaskUseCase.deleteTaskById(id);
        return ResponseEntity.ok().body("Task deleted sucessfully!");
    }
}
