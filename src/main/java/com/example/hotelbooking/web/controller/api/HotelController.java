package com.example.hotelbooking.web.controller.api;

import com.example.hotelbooking.domain.Hotel;
import com.example.hotelbooking.mapper.HotelMapper;
import com.example.hotelbooking.repository.domain.specification.filter.HotelFilter;
import com.example.hotelbooking.service.domain.HotelService;
import com.example.hotelbooking.web.dto.hotel.create.CreateHotelRequestDto;
import com.example.hotelbooking.web.dto.hotel.response.HotelResponseDto;
import com.example.hotelbooking.web.dto.hotel.update.RatingUpdateDto;
import com.example.hotelbooking.web.dto.hotel.update.UpdateHotelRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
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
            @Valid @RequestBody CreateHotelRequestDto hotelRequest
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
            @Valid @RequestBody UpdateHotelRequestDto hotelRequest
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
            @Valid @RequestBody RatingUpdateDto newMark
    ) {
        Hotel updated = hotelService.updateRating(id, newMark.value());

        return ResponseEntity.status(HttpStatus.OK).body(
                hotelMapper.toDto(updated)
        );
    }

    @PostMapping("/search")
    public ResponseEntity<Page<HotelResponseDto>> filter(
            @Valid @RequestBody HotelFilter filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Hotel> result = hotelService.filter(
                filter,
                page,
                size
        );

        return ResponseEntity.status(HttpStatus.OK).body(
                result.map(hotelMapper::toDto)
        );
    }
}