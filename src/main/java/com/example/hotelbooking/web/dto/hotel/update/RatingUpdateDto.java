package com.example.hotelbooking.web.dto.hotel.update;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record RatingUpdateDto(
        @NotNull
        @DecimalMin("1.0")
        @DecimalMax("5.0")
        Double value) {
}