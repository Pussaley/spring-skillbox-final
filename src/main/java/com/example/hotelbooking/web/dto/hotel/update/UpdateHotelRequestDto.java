package com.example.hotelbooking.web.dto.hotel.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHotelRequestDto {

    private String name;
    private String adTitle;
    private String city;
    private String address;
    private double distanceFromCenter;

}