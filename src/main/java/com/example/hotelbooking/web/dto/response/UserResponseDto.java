package com.example.hotelbooking.web.dto.response;

import com.example.hotelbooking.entity.RoleType;
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