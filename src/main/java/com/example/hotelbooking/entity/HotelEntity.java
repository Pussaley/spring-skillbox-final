package com.example.hotelbooking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "hotels")
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name", length = 100, unique = true)
    private String name;
    @Column(name = "ad_title", length = 200)
    private String adTitle;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name = "address", length = 200)
    private String address;
    @Column(name = "distance_from_center")
    private Double distanceFromCenter;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "number_of_ratings")
    private Integer numberOfRatings;
    @OneToMany(
            mappedBy = "hotel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
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