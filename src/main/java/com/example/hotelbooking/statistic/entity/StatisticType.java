package com.example.hotelbooking.statistic.entity;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public enum StatisticType {
    USER_REGISTRATION("questId"),
    ROOM_RESERVATION("questId", "hotelId", "roomId", "checkIn", "checkOut");

    StatisticType(String... headers) {
        this.headers = new LinkedList<>(Arrays.asList(headers));
    }

    private List<String> headers;

    public List<String> getHeaders() {
        return headers;
    }
}