package com.example.hotelbooking.statistic.controller;

import com.example.hotelbooking.statistic.dto.response.StatisticsResponseDto;
import com.example.hotelbooking.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMINISTRATOR')")
public class AdminStatisticController {

    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<byte[]> getStatistics() {

        StatisticsResponseDto statistics = statisticService.getStatistics();
        String contentType = statistics.getContentType();
        byte[] content = statistics.getFileContent();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=statistics.csv")
                .contentType(MediaType.parseMediaType(contentType))
                .body(content);
    }

}