package com.example.hotelbooking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Long id;
    private Date checkInDate;
    private Date checkOutDate;
    private Long roomId;
    private Long hotelId;
    private Long questId;
}