package com.example.hotelbooking.messaging.producer;

import com.example.hotelbooking.messaging.dto.RoomReservationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomReservationKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Long userId, Date checkin, Date checkOut) {
        RoomReservationEvent event = new RoomReservationEvent(userId, checkin, checkOut);
        kafkaTemplate.send("room-reservation-topic", event);
    }

}