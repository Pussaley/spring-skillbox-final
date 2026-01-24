package com.example.hotelbooking.web.dto.booking.create;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingRequestDto {
    @FutureOrPresent(message = "Укажите корректную дату заезда")
    private Date checkIn;
    @Future(message = "Укажите корректную дату выезда")
    private Date checkOut;
    @NotNull(message = "Укажите номер комнаты для бронирования")
    private Long roomId;
}