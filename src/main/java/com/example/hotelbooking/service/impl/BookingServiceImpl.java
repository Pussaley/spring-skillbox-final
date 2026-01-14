package com.example.hotelbooking.service.impl;

import com.example.hotelbooking.mapper.BookingMapper;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

}