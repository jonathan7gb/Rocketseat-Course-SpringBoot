package com.weg.rocketseatcourse.domain.repository;

import com.weg.rocketseatcourse.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    List<User> findAll();
    List<User> findAllByOrderByNameAsc();
    UserDetails findByEmail(String email);
    Optional<User> findById(UUID id);
    List<User> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

    void deleteById(UUID id);

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
}
