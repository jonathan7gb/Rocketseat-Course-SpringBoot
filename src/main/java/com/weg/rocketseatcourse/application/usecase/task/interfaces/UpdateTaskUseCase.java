package com.weg.rocketseatcourse.application.usecase.task.interfaces;

import com.weg.rocketseatcourse.application.dto.task.TaskRequestDTO;
import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;

import java.util.UUID;

public interface UpdateTaskUseCase {

    TaskResponseDTO updateTask(TaskRequestDTO taskRequestDTO, UUID id);

    void startTask(UUID id);
    void endTask(UUID id);
}
