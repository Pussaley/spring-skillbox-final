package com.example.hotelbooking.web.controller;

import com.example.hotelbooking.domain.Room;
import com.example.hotelbooking.mapper.RoomMapper;
import com.example.hotelbooking.service.RoomService;
import com.example.hotelbooking.web.dto.request.RoomRequestDto;
import com.example.hotelbooking.web.dto.response.RoomResponseDto;
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

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDto> findById(
            @PathVariable Long id
    ) {
        Room room = roomService.findById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(
                roomMapper.toDto(room)
        );
    }

    @PostMapping
    public ResponseEntity<RoomResponseDto> create(
            @RequestBody RoomRequestDto roomRequest
    ) {
        Room room = roomMapper.toDomain(roomRequest);
        Room created = roomService.create(room);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                roomMapper.toDto(created)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDto> update(
            @PathVariable Long id,
            @RequestBody RoomRequestDto roomRequest) {

        Room room = roomMapper.toDomain(roomRequest);
        Room updated = roomService.update(id, room);

        return ResponseEntity.status(HttpStatus.OK).body(
                roomMapper.toDto(updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id
    ) {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}