package com.example.hotelbooking.service.domain.impl;

import com.example.hotelbooking.domain.Booking;
import com.example.hotelbooking.domain.Room;
import com.example.hotelbooking.entity.BookingEntity;
import com.example.hotelbooking.mapper.BookingMapper;
import com.example.hotelbooking.messaging.ReservationInfo;
import com.example.hotelbooking.messaging.producer.RoomReservationKafkaProducer;
import com.example.hotelbooking.repository.domain.BookingRepository;
import com.example.hotelbooking.service.domain.BookingService;
import com.example.hotelbooking.service.domain.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;

    private final RoomReservationKafkaProducer roomReservationKafkaProducer;

    private RoomService roomService;

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDomain)
                .toList();
    }

    @Override
    public Booking bookRoom(Booking booking) {

        Room room = roomService.findById(booking.getRoomId());
        Long hotelId = room.getHotelId();

        BookingEntity entity = bookingMapper.toEntity(booking);
        BookingEntity booked = bookingRepository.save(entity);

        roomReservationKafkaProducer.send(new ReservationInfo(
                booking.getQuestId(),
                hotelId,
                booking.getRoomId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        ));

        return bookingMapper.toDomain(booked);
    }
}