package com.example.hotelbooking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String name;
    private String adTitle;
    private String city;
    private String address;
    private double rating;
    private int numberOfRatings;

}