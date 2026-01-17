package com.example.hotelbooking.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum RoleType {
    USER, ADMINISTRATOR;

    public GrantedAuthority toGrantedAuthority() {
        return new SimpleGrantedAuthority(name());
    }
}