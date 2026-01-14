package com.example.hotelbooking.service.impl;

import com.example.hotelbooking.mapper.HotelMapper;
import com.example.hotelbooking.repository.HotelRepository;
import com.example.hotelbooking.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;

}