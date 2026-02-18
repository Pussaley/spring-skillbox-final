package com.example.hotelbooking.statistic.controller;

import com.example.hotelbooking.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMINISTRATOR')")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<Resource> getStatistics() {

        Object statistics = statisticService.getStatistics();


        ByteArrayOutputStream out = new ByteArrayOutputStream();

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }

}