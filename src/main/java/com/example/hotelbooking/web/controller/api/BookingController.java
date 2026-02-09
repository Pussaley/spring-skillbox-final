package com.example.hotelbooking.web.controller.api;

import com.example.hotelbooking.domain.Booking;
import com.example.hotelbooking.mapper.BookingMapper;
import com.example.hotelbooking.service.domain.BookingService;
import com.example.hotelbooking.web.dto.booking.create.BookingRequestDto;
import com.example.hotelbooking.web.dto.booking.response.BookingResponseDto;
import com.example.hotelbooking.web.security.SecurityUserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<List<BookingResponseDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                bookingService.getAll().stream()
                        .map(bookingMapper::toDto)
                        .toList()
        );
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> bookRoom(
            @AuthenticationPrincipal SecurityUserPrincipal principal,
            @Valid @RequestBody BookingRequestDto bookingRequest
    ) {
        Booking booking = bookingMapper.toDomain(bookingRequest);
        booking.setQuestId(principal.getUser().getId());
        Booking booked = bookingService.bookRoom(booking);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                bookingMapper.toDto(booked)
        );
    }

}