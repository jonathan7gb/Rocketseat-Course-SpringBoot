package com.weg.rocketseatcourse.infra.persistence;

import com.weg.rocketseatcourse.domain.entity.Task;
import com.weg.rocketseatcourse.domain.enums.TaskPriority;
import com.weg.rocketseatcourse.domain.repository.TaskRepository;
import com.weg.rocketseatcourse.infra.persistence.jpa.TaskJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskJpaRepository taskJpaRepository;

    public TaskRepositoryImpl(TaskJpaRepository taskJpaRepository){
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public Task save(Task task) {
        return taskJpaRepository.save(task);
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return taskJpaRepository.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return taskJpaRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        taskJpaRepository.deleteById(id);
    }

    @Override
    public List<Task> findByTitleContainingIgnoreCaseOrderByTitleAsc(String title){
        return taskJpaRepository.findByTitleContainingIgnoreCaseOrderByTitleAsc(title);
    }

    @Override
    public List<Task> findByUserId(UUID user_id) {
        return taskJpaRepository.findAllByUser_Id(user_id);
    }

    @Override
    public List<Task> findByPriority(TaskPriority priority) {
        return taskJpaRepository.findAllByPriority(priority);
    }

    @Override
    public boolean existsByUserId(UUID userId){
        return taskJpaRepository.existsByUserId(userId);
    }
}
