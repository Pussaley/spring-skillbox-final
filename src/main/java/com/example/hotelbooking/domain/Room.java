package com.example.hotelbooking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Long id;
    private String name;
    private String description;
    private Integer number;
    private BigDecimal price;
    private Integer maxOccupancy;
    private Long hotelId;
}