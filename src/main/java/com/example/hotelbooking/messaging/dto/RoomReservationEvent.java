package com.example.hotelbooking.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RoomReservationEvent {
    private final Long questId;
    private final Long hotelId;
    private final Long roomId;
    private final Date checkIn;
    private final Date checkOut;
}