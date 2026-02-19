package com.weg.rocketseatcourse.application.usecase.task.interfaces;

import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;

import java.util.List;

public interface FindAllTasksUseCase {

    List<TaskResponseDTO> findAllTasks();
}
