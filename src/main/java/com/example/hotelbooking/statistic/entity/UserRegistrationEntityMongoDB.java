package com.example.hotelbooking.statistic.entity;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "users_registrations")
public class UserRegistrationEntityMongoDB {

    @Id
    private UUID id;

    @Field(value = "quest_id")
    private Long questId;
    private String username;
    @Field(value = "registration_date")
    private LocalDateTime registrationDate;
}