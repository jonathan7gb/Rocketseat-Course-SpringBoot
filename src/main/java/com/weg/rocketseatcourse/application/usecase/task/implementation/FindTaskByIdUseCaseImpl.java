package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.exceptions.TaskNotFoundException;
import com.weg.rocketseatcourse.application.mapper.TaskMapper;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.FindTaskByIdUseCase;
import com.weg.rocketseatcourse.domain.entity.Task;
import com.weg.rocketseatcourse.domain.enums.TaskPriority;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class FindTaskByIdUseCaseImpl implements FindTaskByIdUseCase {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public FindTaskByIdUseCaseImpl(TaskRepository taskRepository, TaskMapper taskMapper){
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponseDTO findTaskByID(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with this Id not found;"));

        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskResponseDTO> findByTitle(String title) {
        List<Task> tasks = taskRepository.findByTitleContainingIgnoreCaseOrderByTitleAsc(title);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("No tasks found!");
        }

        return taskMapper.listEntityToDto(tasks);
    }

    @Override
    public List<TaskResponseDTO> findByUser(UUID user_id) {
        List<Task> tasks = taskRepository.findByUserId(user_id);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("No tasks found!");
        }

        return taskMapper.listEntityToDto(tasks);
    }

    @Override
    public List<TaskResponseDTO> findByPriority(TaskPriority priority) {
        List<Task> tasks = taskRepository.findByPriority(priority);
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("No tasks found!");
        }

        return taskMapper.listEntityToDto(tasks);
    }
}
