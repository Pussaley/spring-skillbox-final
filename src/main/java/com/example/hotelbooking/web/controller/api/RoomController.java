package com.example.hotelbooking.web.controller.api;

import com.example.hotelbooking.domain.Room;
import com.example.hotelbooking.mapper.RoomMapper;
import com.example.hotelbooking.repository.specification.filter.RoomFilter;
import com.example.hotelbooking.service.RoomService;
import com.example.hotelbooking.web.dto.room.create.CreateRoomRequestDto;
import com.example.hotelbooking.web.dto.room.response.RoomResponseDto;
import com.example.hotelbooking.web.dto.room.update.UpdateRoomRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @GetMapping
    public ResponseEntity<List<RoomResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                roomService.findAll().stream()
                        .map(roomMapper::toDto)
                        .toList()
        );
    }

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
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<RoomResponseDto> create(
            @Valid @RequestBody CreateRoomRequestDto roomRequest
    ) {
        Room room = roomMapper.toDomain(roomRequest);
        Room created = roomService.create(room);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                roomMapper.toDto(created)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<RoomResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRoomRequestDto roomRequest) {

        Room room = roomMapper.toDomain(roomRequest);
        Room updated = roomService.update(id, room);

        return ResponseEntity.status(HttpStatus.OK).body(
                roomMapper.toDto(updated)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id
    ) {
        roomService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<RoomResponseDto>> filter(
            @Valid @RequestBody RoomFilter filter,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "20") int pageSize
    ) {

        return ResponseEntity.status(HttpStatus.OK).body(
                roomService.filter(filter, pageNumber, pageSize).map(
                        roomMapper::toDto
                )
        );
    }

}