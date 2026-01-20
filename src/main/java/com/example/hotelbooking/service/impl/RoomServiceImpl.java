package com.example.hotelbooking.service.impl;

import com.example.hotelbooking.domain.Room;
import com.example.hotelbooking.entity.RoomEntity;
import com.example.hotelbooking.exception.EntityNotFoundException;
import com.example.hotelbooking.mapper.RoomMapper;
import com.example.hotelbooking.repository.RoomRepository;
import com.example.hotelbooking.repository.specification.RoomSpecification;
import com.example.hotelbooking.repository.specification.filter.RoomFilter;
import com.example.hotelbooking.service.HotelService;
import com.example.hotelbooking.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    private final HotelService hotelService;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toDomain)
                .toList();
    }

    @Override
    public Room findById(Long id) throws EntityNotFoundException {
        return roomRepository.findById(id).map(roomMapper::toDomain)
                .orElseThrow(
                        () -> new EntityNotFoundException("Комната с таким id не найдена.")
                );
    }

    @Override
    public Room create(Room room) {

        RoomEntity newRoom = roomMapper.toEntity(room);
        RoomEntity createdRoom = roomRepository.save(newRoom);

        return roomMapper.toDomain(createdRoom);
    }

    @Override
    public Room update(Long id, Room room) throws EntityNotFoundException {

        RoomEntity existing = roomRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Комната с таким id не найдена.")
                );

        existing.setName(room.getName());
        existing.setPrice(room.getPrice());
        existing.setDescription(room.getDescription());
        existing.setMaxOccupancy(room.getMaxOccupancy());

        RoomEntity saved = roomRepository.save(existing);

        return roomMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Page<Room> filter(RoomFilter filter, int pageNumber, int pageSize) {

        Specification<RoomEntity> roomSpecification = RoomSpecification.byFilter(filter);
        PageRequest pageRequest = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by("id")
        );

        return roomRepository.findAll(roomSpecification, pageRequest).map(roomMapper::toDomain);
    }
}