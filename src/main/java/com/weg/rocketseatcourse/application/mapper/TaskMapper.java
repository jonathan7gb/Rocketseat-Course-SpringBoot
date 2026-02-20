package com.weg.rocketseatcourse.application.mapper;

import com.weg.rocketseatcourse.application.dto.task.TaskRequestDTO;
import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;
import com.weg.rocketseatcourse.domain.entity.Task;
import com.weg.rocketseatcourse.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {

    private final UserMapper userMapper;

    public TaskMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Task toEntity(TaskRequestDTO taskRequestDTO){
        return new Task(taskRequestDTO.title(), taskRequestDTO.description(), taskRequestDTO.priority(), null);
    }

    public TaskResponseDTO toDto(Task task){
        return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStartAt(), task.getEndAt(), task.getPriority(), userMapper.toDto(task.getUser()), task.getCreatedAt());
    }

    public List<TaskResponseDTO> listEntityToDto(List<Task> tasks){
        List<TaskResponseDTO> responseDTOS = new ArrayList<>();

        for(Task t: tasks){
            responseDTOS.add(toDto(t));
        }

        return responseDTOS;
    }
}
