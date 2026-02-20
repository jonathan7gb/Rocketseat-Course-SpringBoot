package com.weg.rocketseatcourse.domain.entity;

import com.weg.rocketseatcourse.domain.enums.TaskPriority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Task")
public class Task {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 255, nullable = false)
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    private LocalDateTime createdAt;


    public Task(String title, String description, LocalDateTime startAt, LocalDateTime endAt, TaskPriority priority, User user) {
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.priority = priority;
        this.user = user;
    }

    public Task(String title, String description, LocalDateTime startAt, LocalDateTime endAt, TaskPriority priority) {
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.priority = priority;
    }

    public Task(String title, String description, TaskPriority priority, User user) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.user = user;
    }
}
