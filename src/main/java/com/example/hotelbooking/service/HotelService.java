package com.example.hotelbooking.service;

import com.example.hotelbooking.domain.Hotel;
import com.example.hotelbooking.repository.specification.filter.HotelFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HotelService {
    List<Hotel> findAll();
    Hotel findById(Long id);
    Hotel create(Hotel hotel);
    Hotel update(Long id, Hotel hotel);
    void deleteById(Long id);

    Hotel updateRating(Long id, Double newMark);
    Page<Hotel> filter(HotelFilter filter, int pageNumber, int pageSize);
}