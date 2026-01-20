package com.example.hotelbooking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Long id;
    private Date checkInDate;
    private Date checkOutDate;
    private Long roomId;
    private Long questId;
}