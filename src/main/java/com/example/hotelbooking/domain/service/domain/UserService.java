package com.example.hotelbooking.domain.service.domain;

import com.example.hotelbooking.domain.dto.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    User save(User user);
    User update(Long id, User user);
    void deleteById(Long id);
    User findByUsername(String username);

}