package com.example.hotelbooking.web.dto.room.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDto {
    private long id;
    private String name;
    private String description;
    private int number;
    private BigDecimal price;
    private int maxOccupancy;
    private long hotelId;
}