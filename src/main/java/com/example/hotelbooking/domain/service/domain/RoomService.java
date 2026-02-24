package com.example.hotelbooking.domain.service.domain;

import com.example.hotelbooking.domain.dto.Room;
import com.example.hotelbooking.domain.repository.domain.specification.filter.RoomFilter;
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