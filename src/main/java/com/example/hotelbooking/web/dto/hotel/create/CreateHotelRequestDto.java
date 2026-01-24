package com.example.hotelbooking.web.dto.hotel.create;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateHotelRequestDto {
    @NotBlank(message = "Поле name должно быть заполнено")
    private String name;
    @NotBlank(message = "Поле adTitle должно быть заполнено")
    private String adTitle;
    @NotBlank(message = "Поле city должно быть заполнено")
    private String city;
    @NotBlank(message = "Поле address должно быть заполнено")
    private String address;
    @NotNull(message = "Поле distanceFromCenter обязательно к заполнению")
    @Positive(message = "Расстояние до центра указано некорректно, расстояние должно быть больше 0")
    @Digits(integer = 5, fraction = 2)
    private double distanceFromCenter;
}