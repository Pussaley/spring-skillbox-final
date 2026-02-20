package com.example.hotelbooking.statistic.service.impl;

import com.example.hotelbooking.messaging.dto.EventInfoStatisticsDto;
import com.example.hotelbooking.repository.statistics.MongoDBRepository;
import com.example.hotelbooking.statistic.dto.response.StatisticsResponseDto;
import com.example.hotelbooking.statistic.entity.StatisticType;
import com.example.hotelbooking.statistic.entity.StatisticsInfoMongoDB;
import com.example.hotelbooking.statistic.entity.StatisticsKeys;
import com.example.hotelbooking.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MongoDBStatisticService implements StatisticService {

    private final MongoDBRepository statisticRepository;

    @Override
    @SneakyThrows
    public StatisticsResponseDto getStatistics() {

        List<StatisticsInfoMongoDB> data = statisticRepository.findAll();

        Map<StatisticType, List<StatisticsInfoMongoDB>> groupedResults = data.stream()
                .collect(Collectors.groupingBy(StatisticsInfoMongoDB::getType));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        for (Map.Entry<StatisticType, List<StatisticsInfoMongoDB>> entry : groupedResults.entrySet()) {

            List<String> headers = entry.getKey().getHeaders();
            List<StatisticsInfoMongoDB> statisticsInfo = entry.getValue();

            try (CSVPrinter printer = new CSVPrinter(
                    new OutputStreamWriter(out, StandardCharsets.UTF_8),
                    CSVFormat.Builder.create().setHeader(headers.toArray(new String[0])).get())
            ) {
                for (StatisticsInfoMongoDB statisticsInfoMongoDB : statisticsInfo) {

                    List<Object> values = headers.stream()
                            .map(header -> {
                                StatisticsKeys key = StatisticsKeys.valueOf(header);
                                return statisticsInfoMongoDB.getPayload().getOrDefault(key, "");
                            })
                            .collect(Collectors.toList());

                    printer.printRecord(values);
                }
            }

        }

        return new StatisticsResponseDto(
                "text/csv",
                out.toByteArray()
        );
    }

    @Override
    public void save(EventInfoStatisticsDto eventDto) {

        StatisticType eventType = eventDto.getEventType();
        Map<StatisticsKeys, Object> payload = eventDto.getData();

        StatisticsInfoMongoDB data = new StatisticsInfoMongoDB();
        data.setId(UUID.randomUUID());
        data.setTimestamp(LocalDateTime.now());
        data.setType(eventType);
        data.setPayload(payload);

        statisticRepository.save(data);
    }
}