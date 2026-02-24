package com.example.hotelbooking.messaging.consumer;

import com.example.hotelbooking.messaging.dto.EventInfoStatisticsDto;
import com.example.hotelbooking.messaging.events.RoomReservationEvent;
import com.example.hotelbooking.messaging.events.UserRegistrationEvent;
import com.example.hotelbooking.statistic.entity.StatisticType;
import com.example.hotelbooking.statistic.entity.StatisticsKeys;
import com.example.hotelbooking.statistic.service.impl.MongoDBStatisticService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class StatisticsKafkaListener {

    private final MongoDBStatisticService statisticService;

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
        Map<StatisticsKeys, Object> fields = fillUserRegistrationEventFields(event);

        EventInfoStatisticsDto statisticsDto = new EventInfoStatisticsDto(
                StatisticType.USER_REGISTRATION,
                Instant.ofEpochMilli(timestamp)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime(),
                fields
        );

        statisticService.save(statisticsDto);
    }

    @KafkaListener(
            topics = "room-reservation-topic",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenTopic(
            @Payload RoomReservationEvent event,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp
    ) {
        Map<StatisticsKeys, Object> fields = fillRoomReservationEventFields(event);

        EventInfoStatisticsDto statisticsDto = new EventInfoStatisticsDto(
                StatisticType.ROOM_RESERVATION,
                Instant.ofEpochMilli(timestamp)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime(),
                fields
        );
        statisticService.save(statisticsDto);
    }

    private Map<StatisticsKeys, Object> fillUserRegistrationEventFields(@NonNull UserRegistrationEvent event) {
        return Map.of(
                StatisticsKeys.QUEST_ID, event.getQuestId()
        );
    }

    private Map<StatisticsKeys, Object> fillRoomReservationEventFields(@NonNull RoomReservationEvent event) {
        Map<StatisticsKeys, Object> fields = new HashMap<>();

        fields.put(StatisticsKeys.QUEST_ID, event.getQuestId());
        fields.put(StatisticsKeys.HOTEL_ID, event.getHotelId());
        fields.put(StatisticsKeys.ROOM_ID, event.getRoomId());
        fields.put(StatisticsKeys.CHECK_IN_DATE, event.getCheckIn());
        fields.put(StatisticsKeys.CHECK_OUT_DATE, event.getCheckOut());

        return fields;
    }

}