package com.example.hotelbooking.statistic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticsResponseDto {
    private String contentType;
    private byte[] fileContent;
}