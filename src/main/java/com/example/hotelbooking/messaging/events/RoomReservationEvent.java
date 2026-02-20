package com.example.hotelbooking.messaging.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@AllArgsConstructor
@Getter
public class RoomReservationEvent {
    private final Long questId;
    private final Long hotelId;
    private final Long roomId;
    private final Date checkIn;
    private final Date checkOut;
}