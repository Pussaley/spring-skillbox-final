package com.example.hotelbooking.repository.specification.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomFilter {

    private Long hotelId;
    private Long roomId;
    private String roomName;
    private Date checkIn;
    private Date checkOut;
    private Integer occupancy;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

}