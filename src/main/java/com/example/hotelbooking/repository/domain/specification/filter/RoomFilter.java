package com.example.hotelbooking.repository.domain.specification.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomFilter {

    private Long roomId;
    private String roomName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer occupancy;
    private Date checkIn;
    private Date checkOut;
    private Long hotelId;

}