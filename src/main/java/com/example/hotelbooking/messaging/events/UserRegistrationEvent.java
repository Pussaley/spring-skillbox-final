package com.example.hotelbooking.messaging.events;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationEvent {
    private Long questId;
}