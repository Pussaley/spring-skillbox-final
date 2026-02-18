package com.example.hotelbooking.messaging.consumer;

import com.example.hotelbooking.messaging.dto.UserRegistrationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationKafkaListener {

    @KafkaListener(
            topics = "user-registration-topic",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenTopic(
            @Payload UserRegistrationEvent event,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
    ) {
        log.info("Received the message ...");

        // some logic ..

        log.info("Processed the message ...");
    }

}