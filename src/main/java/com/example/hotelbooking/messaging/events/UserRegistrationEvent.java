package com.example.hotelbooking.messaging.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Getter
public class UserRegistrationEvent {
    private final Long questId;
}