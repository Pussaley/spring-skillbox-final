package com.example.hotelbooking.statistic.service;

import com.example.hotelbooking.messaging.dto.EventInfoStatisticsDto;
import com.example.hotelbooking.statistic.dto.response.StatisticsResponseDto;

public interface StatisticService {
    StatisticsResponseDto getStatistics();
    void save(EventInfoStatisticsDto eventInfoStatisticsDto);
}