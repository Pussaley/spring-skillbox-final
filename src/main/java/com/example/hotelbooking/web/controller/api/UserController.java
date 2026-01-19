package com.example.hotelbooking.web.controller.api;

import com.example.hotelbooking.domain.User;
import com.example.hotelbooking.mapper.UserMapper;
import com.example.hotelbooking.service.UserService;
import com.example.hotelbooking.web.dto.user.create.UserRequestDto;
import com.example.hotelbooking.web.dto.user.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {

        return ResponseEntity.status(HttpStatus.FOUND).body(
                userService.findAll().stream()
                        .map(userMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(
            @PathVariable Long id
    ) {
        User user = userService.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(
                userMapper.toDto(user)
        );
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(
            @RequestBody UserRequestDto userRequest
    ) {
        User user = userMapper.toDomain(userRequest);
        User created = userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                userMapper.toDto(created)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable Long id,
            @RequestBody UserRequestDto userRequest) {

        User user = userMapper.toDomain(userRequest);
        User updated = userService.update(id, user);

        return ResponseEntity.status(HttpStatus.OK).body(
                userMapper.toDto(updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id
    ) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}