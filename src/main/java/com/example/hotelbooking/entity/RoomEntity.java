package com.example.hotelbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200)
    private String name;
    @Column(columnDefinition = "LONGTEXT(1000)")
    private String description;
    private int room;
    private BigDecimal price;
    private int maxOccupancy;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
    /*
    TODO: рассмотреть вариант хранения в отдельной таблице
    private List<Date> unavailabilityDays;
    */
}