package com.weg.rocketseatcourse.application.usecase.task.interfaces;

import com.weg.rocketseatcourse.application.dto.task.TaskRequestDTO;
import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;

public interface CreateTaskUseCase {

    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO);
}
