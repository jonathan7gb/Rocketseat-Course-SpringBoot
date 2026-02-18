package com.weg.rocketseatcourse.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

import java.util.UUID;

public interface TaskRepositoryImpl extends JpaRepository<UUID, Task> {
}
