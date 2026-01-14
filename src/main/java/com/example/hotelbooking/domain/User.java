package com.example.hotelbooking.domain;

import com.example.hotelbooking.entity.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String email;
    private RoleType role;
    private Set<Booking> bookings;
}