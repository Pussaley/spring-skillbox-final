package com.example.hotelbooking.service.impl;

import com.example.hotelbooking.domain.Booking;
import com.example.hotelbooking.entity.BookingEntity;
import com.example.hotelbooking.mapper.BookingMapper;
import com.example.hotelbooking.repository.BookingRepository;
import com.example.hotelbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDomain)
                .toList();
    }

    @Override
    public Booking bookRoom(Booking booking) {

        BookingEntity entity = bookingMapper.toEntity(booking);
        BookingEntity booked = bookingRepository.save(entity);

        return bookingMapper.toDomain(booked);
    }
}