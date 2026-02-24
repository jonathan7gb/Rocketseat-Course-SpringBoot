package com.weg.rocketseatcourse.presentation.controller;

import com.weg.rocketseatcourse.application.dto.user.UserRequestDTO;
import com.weg.rocketseatcourse.application.dto.user.UserResponseDTO;

import com.weg.rocketseatcourse.application.usecase.user.interfaces.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

    @RestController
    @RequestMapping("/users")
    public class UserController {
    
        private final CreateUserUseCase createUserUseCase;
        private final FindAllUsersUseCase findAllUsersUseCase;
        private final FindUserByIdUseCase findUserByIdUseCase;
        private final UpdateUserUseCase updateUserUseCase;
        private final DeleteUserUseCase deleteUserUseCase;
    
        public UserController(
                CreateUserUseCase createUserUseCase,
                FindAllUsersUseCase findAllUsersUseCase,
                FindUserByIdUseCase findUserByIdUseCase,
                UpdateUserUseCase updateUserUseCase,
                DeleteUserUseCase deleteUserUseCase
        ){
            this.createUserUseCase = createUserUseCase;
            this.findAllUsersUseCase = findAllUsersUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userCreated = createUserUseCase.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAllUsers(){
        List<UserResponseDTO> userResponseDTOS = findAllUsersUseCase.findAllUsers();
        return ResponseEntity.ok().body(userResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable UUID id){
        UserResponseDTO userResponseDTO = findUserByIdUseCase.findUserByID(id);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO, @PathVariable UUID id){
        UserResponseDTO userResponseDTO = updateUserUseCase.updateUser(userRequestDTO, id);
        return ResponseEntity.ok().body(userResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id){
        deleteUserUseCase.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchbyname/{name}")
    public ResponseEntity<List<UserResponseDTO>> findByName(@PathVariable String name){
        List<UserResponseDTO> responseDTOS = findUserByIdUseCase.findByName(name);
        return ResponseEntity.ok().body(responseDTOS);
    }
}
