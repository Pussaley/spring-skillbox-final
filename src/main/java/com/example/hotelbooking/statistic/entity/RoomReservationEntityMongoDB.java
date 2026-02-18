package com.example.hotelbooking.statistic.entity;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.UUID;

@Document(collection = "reservations")
public class RoomReservationEntityMongoDB {

    @Id
    private UUID id;

    @Field(value = "hotel_id")
    private Long hotelId;
    @Field(value = "room_id")
    private Long roomId;
    @Field(value = "quest_id")
    private Long questId;
    @Field(value = "check_in")
    private Date checkIn;
    @Field(value = "check_Out")
    private Date checkOut;

}