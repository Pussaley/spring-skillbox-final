package com.example.hotelbooking.service.impl;

import com.example.hotelbooking.mapper.RoomMapper;
import com.example.hotelbooking.repository.RoomRepository;
import com.example.hotelbooking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;

}