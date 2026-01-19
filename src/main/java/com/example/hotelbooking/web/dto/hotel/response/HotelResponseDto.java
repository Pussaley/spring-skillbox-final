package com.example.hotelbooking.web.dto.hotel.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelResponseDto {

    private Long id;
    private String name;
    private String adTitle;
    private String city;
    private String address;
    private double rating;
    private int numberOfRatings;

}