package com.weg.rocketseatcourse.infra.persistence;

import com.weg.rocketseatcourse.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositoryImpl extends JpaRepository<UUID, User>  {
}
