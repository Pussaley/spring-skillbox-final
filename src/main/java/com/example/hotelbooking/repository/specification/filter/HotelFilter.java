package com.example.hotelbooking.repository.specification.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelFilter {

    private Long hotelId;
    private String name;
    private String adTitle;
    private String city;
    private String address;
    private Double distanceFromCenter;
    private Double totalRating;

}