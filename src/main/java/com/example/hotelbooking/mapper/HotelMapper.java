package com.example.hotelbooking.mapper;

import com.example.hotelbooking.config.mapper.MapstructMapperConfiguration;
import com.example.hotelbooking.domain.Hotel;
import com.example.hotelbooking.entity.HotelEntity;
import com.example.hotelbooking.web.dto.hotel.create.CreateHotelRequestDto;
import com.example.hotelbooking.web.dto.hotel.response.HotelResponseDto;
import com.example.hotelbooking.web.dto.hotel.update.UpdateHotelRequestDto;
import org.mapstruct.Mapper;

@Mapper(
        config = MapstructMapperConfiguration.class
)
public interface HotelMapper {
    Hotel toDomain(CreateHotelRequestDto request);
    Hotel toDomain(UpdateHotelRequestDto request);
    Hotel toDomain(HotelEntity entity);
    HotelEntity toEntity(Hotel hotel);
    HotelResponseDto toDto(Hotel hotel);
}