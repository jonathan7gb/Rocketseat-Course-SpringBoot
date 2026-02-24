package com.weg.rocketseatcourse.domain.repository;

import com.weg.rocketseatcourse.domain.entity.Task;
import com.weg.rocketseatcourse.domain.enums.TaskPriority;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {

    Task save(Task task);

    Optional<Task> findById(UUID id);
    List<Task> findAll();
    List<Task> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title);
    List<Task> findByUserId(UUID user_id);
    List<Task> findByPriority(TaskPriority priority);
    boolean existsByUserId(UUID userId);

    void deleteById(UUID id);
}
