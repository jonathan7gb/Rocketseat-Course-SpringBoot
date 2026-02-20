package com.weg.rocketseatcourse.application.usecase.task.interfaces;

import com.weg.rocketseatcourse.application.dto.task.TaskResponseDTO;

import java.util.List;
import java.util.UUID;


public interface FindTaskByIdUseCase {

    TaskResponseDTO findTaskByID(UUID id);
    List<TaskResponseDTO> findByTitle(String title);
    List<TaskResponseDTO> findByUser(UUID user_id);

}
