package com.weg.rocketseatcourse.application.mapper;

import com.weg.rocketseatcourse.application.dto.task.TaskRequestDTO;
import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;
import com.weg.rocketseatcourse.domain.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequestDTO taskRequestDTO){
        return new Task(taskRequestDTO.title(), taskRequestDTO.description(), taskRequestDTO.startAt(), taskRequestDTO.endAt(), taskRequestDTO.priority(), taskRequestDTO.user_id());
    }

    public TaskResponseDTO toDto(Task task){
        return new TaskResponseDTO(task.getId(), task.getTitle(), task.getDescription(), task.getStartAt(), task.getEndAt(), task.getPriority(), task.getUser_id(), task.getCreatedAt());
    }
}
