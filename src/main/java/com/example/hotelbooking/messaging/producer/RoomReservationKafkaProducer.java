package com.example.hotelbooking.messaging.producer;

import com.example.hotelbooking.messaging.ReservationInfo;
import com.example.hotelbooking.messaging.dto.RoomReservationEvent;
import lombok.NonNull;
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

    public void send(@NonNull ReservationInfo data) {

        Date checkIn = data.checkIn();
        Date checkOut = data.checkOut();
        Long questId = data.questId();
        Long roomId = data.roomId();
        Long hotelId = data.hotelId();

        RoomReservationEvent event = new RoomReservationEvent(
                questId, hotelId, roomId, checkIn, checkOut
        );
        kafkaTemplate.send("room-reservation-topic", event);
    }

}