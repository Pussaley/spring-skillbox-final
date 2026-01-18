package com.example.hotelbooking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hotels")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String name;
    @Column(name = "ad_title", length = 100)
    private String adTitle;
    @Column(length = 50)
    private String city;
    private String address;
    private double rating;
    @Column(name = "number_of_ratings")
    private int numberOfRatings;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @EqualsAndHashCode.Exclude
    private Set<RoomEntity> rooms = new HashSet<>();

    public void addRoom(RoomEntity room) {
        room.setHotel(this);
        this.rooms.add(room);
    }

    public void removeRoom(RoomEntity room) {
        room.setHotel(null);
        this.rooms.remove(room);
    }

}