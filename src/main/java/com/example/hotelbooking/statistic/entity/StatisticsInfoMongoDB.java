package com.example.hotelbooking.statistic.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Data
@Document(collection = "statistics_data")
public class StatisticsInfoMongoDB {

    @Id
    private UUID id;

    private StatisticType type;
    private LocalDateTime timestamp;

    private Map<StatisticsKeys, Object> payload = new LinkedHashMap<>();
}