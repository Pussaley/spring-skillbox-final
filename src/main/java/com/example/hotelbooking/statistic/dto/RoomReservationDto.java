package com.example.hotelbooking.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomReservationDto {

    private Long hotelId;
    private Long roomId;
    private Long questId;
    private LocalDate checkIn;
    private LocalDate checkOut;

}