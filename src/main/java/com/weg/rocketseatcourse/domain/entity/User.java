package com.weg.rocketseatcourse.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotNull
    @NotEmpty
    @Column(length = 100)
    private String name;

    @Email
    @NotEmpty
    @NotNull
    @Column(length = 100, unique = true)
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
