package com.weg.rocketseatcourse.infra.persistence.jpa;


import com.weg.rocketseatcourse.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<Task, UUID> {
}
