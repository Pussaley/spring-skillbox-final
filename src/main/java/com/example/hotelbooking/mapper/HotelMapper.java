package com.example.hotelbooking.mapper;

import com.example.hotelbooking.config.mapper.MapstructMapperConfiguration;
import com.example.hotelbooking.domain.Hotel;
import com.example.hotelbooking.entity.HotelEntity;
import com.example.hotelbooking.web.dto.request.HotelRequestDto;
import com.example.hotelbooking.web.dto.response.HotelResponseDto;
import org.mapstruct.Mapper;

@Mapper(
        config = MapstructMapperConfiguration.class
)
public interface HotelMapper {
    Hotel toDomain(HotelRequestDto request);
    Hotel toDomain(HotelEntity entity);
    HotelEntity toEntity(Hotel hotel);
    HotelResponseDto toDto(Hotel hotel);
}