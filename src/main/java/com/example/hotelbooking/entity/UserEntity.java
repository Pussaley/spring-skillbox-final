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

import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String username;
    private String password;
    private String email;
    private RoleType role;
    @OneToMany(
            mappedBy = "quest",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<BookingEntity> bookings;

    public void addBooking(BookingEntity booking) {
        booking.setQuest(this);
        this.bookings.add(booking);
    }

    public void removeBooking(BookingEntity booking) {
        booking.setQuest(null);
        this.bookings.remove(booking);
    }
}