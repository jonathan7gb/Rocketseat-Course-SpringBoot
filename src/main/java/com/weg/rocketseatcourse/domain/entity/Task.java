package com.weg.rocketseatcourse.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    @NotEmpty
    @NotNull
    @Column(length = 50)
    private String title;

    @NotEmpty
    @NotNull
    @Column(length = 255)
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    private UUID user_id;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
