package com.weg.rocketseatcourse.infra.persistence.jpa;


import com.weg.rocketseatcourse.domain.entity.Task;
import com.weg.rocketseatcourse.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<Task, UUID> {

    List<Task> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title);
}
