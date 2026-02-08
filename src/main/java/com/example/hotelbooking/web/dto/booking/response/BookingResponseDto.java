package com.example.hotelbooking.web.dto.booking.response;

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
    private Long roomId;
    private Long questId;
}