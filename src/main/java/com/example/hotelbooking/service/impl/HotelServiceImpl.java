package com.example.hotelbooking.service.impl;

import com.example.hotelbooking.domain.Hotel;
import com.example.hotelbooking.entity.HotelEntity;
import com.example.hotelbooking.exception.EntityNotFoundException;
import com.example.hotelbooking.mapper.HotelMapper;
import com.example.hotelbooking.repository.HotelRepository;
import com.example.hotelbooking.repository.specification.HotelSpecification;
import com.example.hotelbooking.repository.specification.filter.HotelFilter;
import com.example.hotelbooking.service.HotelService;
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
public class HotelServiceImpl implements HotelService {

    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;

    @Override
    public List<Hotel> findAll() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::toDomain)
                .toList();
    }

    @Override
    public Hotel findById(Long id) throws EntityNotFoundException {
        return hotelRepository.findById(id)
                .map(hotelMapper::toDomain)
                .orElseThrow(
                        () -> new EntityNotFoundException("Отель с таким id не найден.")
                );
    }

    @Override
    public Hotel create(Hotel hotel) {
        HotelEntity entity = hotelMapper.toEntity(hotel);

        entity.setNumberOfRatings(0);
        entity.setRating(0);

        HotelEntity saved = hotelRepository.save(entity);

        return hotelMapper.toDomain(saved);
    }

    @Override
    public Hotel update(Long id, Hotel hotel) throws EntityNotFoundException {

        HotelEntity existing = hotelRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Отель с таким id не найден.")
                );

        existing.setAddress(hotel.getAddress());
        existing.setCity(hotel.getCity());
        existing.setName(hotel.getName());
        existing.setRating(hotel.getRating());
        existing.setAdTitle(hotel.getAdTitle());

        return hotelMapper.toDomain(existing);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel updateRating(Long id, Double newMark) {
        HotelEntity existing = hotelRepository.findById(id).orElseThrow();

        Hotel hotel = hotelMapper.toDomain(existing);
        hotel.calculateTotalRating(newMark);

        existing.setRating(hotel.getRating());
        existing.setNumberOfRatings(hotel.getNumberOfRatings());

        HotelEntity updated = hotelRepository.save(existing);

        return hotelMapper.toDomain(updated);
    }

    @Override
    public Page<Hotel> filter(HotelFilter filter, int pageNumber, int pageSize) {

        Specification<HotelEntity> hotelSpecification = HotelSpecification.byFilter(filter);
        PageRequest pageRequest = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by("id")
        );

        return hotelRepository.findAll(hotelSpecification, pageRequest)
                .map(hotelMapper::toDomain);
    }
}