package com.weg.rocketseatcourse.domain.repository;

import com.weg.rocketseatcourse.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UUID, User> {
    User findByEmail(String email);
}
