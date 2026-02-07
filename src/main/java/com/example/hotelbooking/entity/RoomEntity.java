package com.example.hotelbooking.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "rooms")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "number", nullable = false)
    private Integer number;
    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;
    @Column(name = "max_occupancy", nullable = false)
    private Integer maxOccupancy;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private HotelEntity hotel;
    @OneToMany(
            mappedBy = "room",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<BookingEntity> bookings = new HashSet<>();

    public void addBooking(BookingEntity booking) {
        booking.setRoom(this);
        this.bookings.add(booking);
    }

    public void removeBooking(BookingEntity booking) {
        booking.setRoom(null);
        this.bookings.remove(booking);
    }

}