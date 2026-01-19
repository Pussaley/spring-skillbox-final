package com.example.hotelbooking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private Long id;
    private String name;
    private String adTitle;
    private String city;
    private String address;
    private double distanceFromCenter;
    private double rating;
    private int numberOfRatings;

    public Hotel calculateTotalRating(double newMark) {
        double totalRating = this.rating * this.numberOfRatings;
        totalRating = totalRating - this.rating + newMark;
        this.rating = totalRating / numberOfRatings;
        this.numberOfRatings++;

        return this;
    }

}