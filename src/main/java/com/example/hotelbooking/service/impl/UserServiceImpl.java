package com.example.hotelbooking.service.impl;

import com.example.hotelbooking.mapper.UserMapper;
import com.example.hotelbooking.repository.UserRepository;
import com.example.hotelbooking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

}