package com.example.hotelbooking.web.dto.booking.response;

import com.example.hotelbooking.web.dto.room.response.RoomResponseDto;
import com.example.hotelbooking.web.dto.user.response.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {
    private Long id;
    private Date checkInDate;
    private Date checkOutDate;
    private RoomResponseDto room;
    private UserResponseDto quest;
}