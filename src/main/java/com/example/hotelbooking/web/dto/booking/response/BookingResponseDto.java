package com.example.hotelbooking.web.dto.booking.response;

import com.example.hotelbooking.web.dto.user.response.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingResponseDto {
    private Long id;
    private Date checkInDate;
    private Date checkOutDate;
    private String bookedRoomInformation;
    private UserResponseDto quest;
}