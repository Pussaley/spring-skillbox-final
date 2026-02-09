package com.example.hotelbooking.service.domain;

import com.example.hotelbooking.domain.Room;
import com.example.hotelbooking.repository.specification.filter.RoomFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RoomService {
    List<Room> findAll();
    Room findById(Long id);
    Room create(Room room);
    Room update(Long id, Room room);
    void deleteById(Long id);

    Page<Room> filter(RoomFilter filter, int pageNumber, int pageSize);
}