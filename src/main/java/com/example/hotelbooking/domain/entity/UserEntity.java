package com.example.hotelbooking.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private RoleType role;
    @OneToMany(
            mappedBy = "quest",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<BookingEntity> bookings = new HashSet<>();

    @Column(name = "non_expired", nullable = false)
    private boolean accountNonExpired;
    @Column(name = "non_locked", nullable = false)
    private boolean accountNonLocked;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "credentials_non_expired", nullable = false)
    private boolean credentialsNonExpired;

    public void addBooking(BookingEntity booking) {
        booking.setQuest(this);
        this.bookings.add(booking);
    }

    public void removeBooking(BookingEntity booking) {
        booking.setQuest(null);
        this.bookings.remove(booking);
    }
}