package com.example.hotelbooking.service;

import com.example.hotelbooking.domain.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();
    Room findById(Long id);
    Room create(Room room);
    Room update(Long id, Room room);
    void deleteById(Long id);
}