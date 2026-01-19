package com.example.hotelbooking.web.dto.room.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomRequestDto {

    private String name;
    private String description;
    private int number;
    private BigDecimal price;
    private long maxOccupancy;
    private long hotelId;

}