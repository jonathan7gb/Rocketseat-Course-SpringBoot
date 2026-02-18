package com.weg.rocketseatcourse.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @NotNull(message = "Name can't be null")
    @NotEmpty(message = "Name can't be empty")
    @Column(length = 100)
    private String name;

    @Email(message = "Invalid E-mail")
    @NotEmpty(message = "E-mail can't be empty")
    @NotNull(message = "E-mail can't be null")
    @Column(length = 100, unique = true)
    private String email;

    @NotNull(message = "Password can't be null")
    @NotEmpty(message = "Password can't be empty")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void encryptPassword(PasswordEncoder encoder) {
        this.password = encoder.encode(this.password);
    }
}
