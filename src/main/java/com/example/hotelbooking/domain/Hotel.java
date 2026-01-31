package com.example.hotelbooking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private Long id;
    private String name;
    private String adTitle;
    private String city;
    private String address;
    private Double distanceFromCenter;
    private Double rating;
    private int numberOfRatings;

    public void calculateTotalRating(double newMark) {

        BigDecimal currentRating = BigDecimal.valueOf(this.rating);
        BigDecimal currentNumbOfRatings = BigDecimal.valueOf(this.numberOfRatings);
        BigDecimal newMarkBD = BigDecimal.valueOf(newMark);

        BigDecimal totalSum = currentRating.multiply(currentNumbOfRatings).add(newMarkBD);

        this.numberOfRatings++;

        BigDecimal newAverage = totalSum.divide(BigDecimal.valueOf(this.numberOfRatings), 2, RoundingMode.HALF_UP);

        this.rating = newAverage.doubleValue();
    }

}