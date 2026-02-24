package com.example.hotelbooking.domain.service.domain;

import com.example.hotelbooking.domain.dto.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> getAll();
    Booking bookRoom(Booking booking);

}