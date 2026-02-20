package com.weg.rocketseatcourse.application.usecase.task.implementation;

import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.exceptions.TaskNotFoundException;
import com.weg.rocketseatcourse.application.mapper.TaskMapper;
import com.weg.rocketseatcourse.application.usecase.task.interfaces.FindAllTasksUseCase;
import com.weg.rocketseatcourse.domain.entity.Task;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindAllTasksUseCaseImpl implements FindAllTasksUseCase {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public FindAllTasksUseCaseImpl(TaskRepository taskRepository, TaskMapper taskMapper){
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskResponseDTO> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if(tasks.isEmpty()){
            throw new TaskNotFoundException("No tasks found!");
        }

        return taskMapper.listEntityToDto(tasks);
    }
}
