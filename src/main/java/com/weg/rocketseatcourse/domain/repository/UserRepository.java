package com.weg.rocketseatcourse.domain.repository;

import com.weg.rocketseatcourse.domain.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(UUID id);
    List<User> findAll();
    void deleteById(UUID id);
    Optional<User>  findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);
    List<User> findAllByOrderByNameAsc();
}
