package com.example.hotelbooking.web.dto.room.create;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomRequestDto {
    @NotBlank(message = "Поле name обязательно к заполнению")
    private String name;
    @NotBlank(message = "Поле description обязательно к заполнению")
    private String description;
    @NotNull(message = "Номер комнаты должен быть указан")
    @Positive(message = "Номер комнаты должен быть отличным от нуля")
    @Digits(integer = 4, fraction = 0)
    private int number;
    @NotNull(message = "Цена бронивания комнаты должна быть указана")
    @Positive(message = "Стоимость комнаты указана некорректно, стоимость должна быть положительным числом")
    @Digits(integer = 17, fraction = 2, message = "Некорректный формат стоимости")
    private BigDecimal price;
    @NotNull(message = "Поле maxOccupancy обязательно к заполнению")
    @Digits(integer = 2, fraction = 0)
    private long maxOccupancy;
    @NotNull(message = "Поле hotelId обязательно к заполнению")
    private long hotelId;
}