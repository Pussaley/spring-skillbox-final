package com.example.hotelbooking.messaging.dto;

import com.example.hotelbooking.statistic.entity.StatisticType;
import com.example.hotelbooking.statistic.entity.StatisticsKeys;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
public class EventInfoStatisticsDto {
    private UUID id;
    private final StatisticType eventType;
    private final LocalDateTime timestamp;
    private final Map<StatisticsKeys, Object> data;
}