package com.example.hotelbooking.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDto {
    private Long id;
    private String name;
    private String description;
    private int room;
    private BigDecimal price;
    private int maxOccupancy;
    private List<Date> unavailabilityDays;
}