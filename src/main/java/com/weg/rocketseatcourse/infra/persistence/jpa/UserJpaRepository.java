package com.weg.rocketseatcourse.infra.persistence.jpa;

import com.weg.rocketseatcourse.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
    List<User> findAllByOrderByNameAsc();
    List<User> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

}
