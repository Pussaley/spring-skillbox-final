package com.example.hotelbooking.web.dto.response;

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