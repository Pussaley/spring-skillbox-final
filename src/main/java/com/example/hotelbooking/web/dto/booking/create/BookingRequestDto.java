package com.example.hotelbooking.web.dto.booking.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingRequestDto {
    private Date checkIn;
    private Date checkOut;
    private Long roomId;
}