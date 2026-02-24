package com.example.hotelbooking.web.dto.user.response;

import com.example.hotelbooking.domain.entity.RoleType;
import com.example.hotelbooking.web.dto.booking.response.BookingResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private RoleType role;
    private Set<BookingResponseDto> bookings;
}