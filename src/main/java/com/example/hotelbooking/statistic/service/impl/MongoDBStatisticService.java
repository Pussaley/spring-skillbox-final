package com.example.hotelbooking.statistic.service.impl;

import com.example.hotelbooking.repository.statistics.MongoDBRepository;
import com.example.hotelbooking.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MongoDBStatisticService implements StatisticService {

    private final MongoDBRepository statisticRepository;

    @Override
    public Object getStatistics() {

        CSVFormat.DEFAULT.builder()

                .get();

        return statisticRepository.findById(UUID.randomUUID());
    }
}