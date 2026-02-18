package com.example.hotelbooking.messaging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationEvent {
    private final Long userId;
}