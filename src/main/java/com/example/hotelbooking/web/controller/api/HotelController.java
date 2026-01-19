package com.example.hotelbooking.web.controller.api;

import com.example.hotelbooking.domain.Hotel;
import com.example.hotelbooking.mapper.HotelMapper;
import com.example.hotelbooking.service.HotelService;
import com.example.hotelbooking.web.dto.hotel.update.RatingUpdateDto;
import com.example.hotelbooking.web.dto.hotel.create.CreateHotelRequestDto;
import com.example.hotelbooking.web.dto.hotel.response.HotelResponseDto;
import com.example.hotelbooking.web.dto.hotel.update.UpdateHotelRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelMapper hotelMapper;
    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<HotelResponseDto>> findAll() {
        return ResponseEntity.ok(
                hotelService.findAll().stream()
                        .map(hotelMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDto> findById(
            @PathVariable Long id
    ) {

        Hotel found = hotelService.findById(id);

        return ResponseEntity.ok(
                hotelMapper.toDto(found)
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<HotelResponseDto> create(
            @RequestBody CreateHotelRequestDto hotelRequest
    ) {

        Hotel hotel = hotelMapper.toDomain(hotelRequest);
        Hotel createdHotel = hotelService.create(hotel);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                hotelMapper.toDto(createdHotel)
        );
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<HotelResponseDto> create(
            @PathVariable Long id,
            @RequestBody UpdateHotelRequestDto hotelRequest
    ) {

        Hotel hotel = hotelMapper.toDomain(hotelRequest);
        Hotel updatedHotel = hotelService.update(id, hotel);

        return ResponseEntity.ok(
                hotelMapper.toDto(updatedHotel)
        );
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<HotelResponseDto> deleteById(
            @PathVariable Long id
    ) {

        hotelService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/rating/{id}")
    public ResponseEntity<HotelResponseDto> updateRating(
            @PathVariable Long id,
            @RequestBody RatingUpdateDto newMark
    ) {
        Hotel updated = hotelService.updateRating(id, newMark.value());

        return ResponseEntity.status(HttpStatus.OK).body(
                hotelMapper.toDto(updated)
        );
    }
}