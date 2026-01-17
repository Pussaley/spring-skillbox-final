package com.example.hotelbooking.service;

import com.example.hotelbooking.domain.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> getAll();
    Booking bookRoom(Booking booking);

}