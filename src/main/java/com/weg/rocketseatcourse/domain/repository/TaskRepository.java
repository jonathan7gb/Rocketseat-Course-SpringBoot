package com.weg.rocketseatcourse.domain.repository;

import com.weg.rocketseatcourse.domain.entity.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository {

    Task save(Task task);
    Optional<Task> findById(UUID id);
    List<Task> findAll();
    void deleteById(UUID id);
}
