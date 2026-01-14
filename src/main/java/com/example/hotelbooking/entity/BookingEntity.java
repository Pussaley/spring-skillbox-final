package com.example.hotelbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(value = TemporalType.DATE)
    @Column(name = "check_in")
    private Date checkInDate;
    @Temporal(value = TemporalType.DATE)
    @Column(name = "check_out")
    private Date checkOutDate;
    private String bookedRoomInformation;
    @ManyToOne
    @JoinColumn(name = "quest_id")
    private UserEntity quest;

}
