package com.example.hotelbooking.messaging;

import java.util.Date;

public record ReservationInfo(Long questId, Long hotelId, Long roomId, Date checkIn, Date checkOut) {}