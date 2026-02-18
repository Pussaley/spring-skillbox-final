package com.example.hotelbooking.messaging.producer;

import com.example.hotelbooking.messaging.dto.UserRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Long userId) {
        UserRegistrationEvent event = new UserRegistrationEvent(userId);
        kafkaTemplate.send("user-registration-topic", event);
    }

}