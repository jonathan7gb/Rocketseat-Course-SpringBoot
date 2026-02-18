package com.weg.rocketseatcourse.domain.repository;

import com.weg.rocketseatcourse.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<UUID, Task> {
}
