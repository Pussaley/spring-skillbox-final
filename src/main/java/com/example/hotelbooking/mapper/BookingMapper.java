package com.example.hotelbooking.mapper;

import com.example.hotelbooking.config.mapper.MapstructMapperConfiguration;
import com.example.hotelbooking.domain.Booking;
import com.example.hotelbooking.entity.BookingEntity;
import com.example.hotelbooking.web.dto.booking.create.BookingRequestDto;
import com.example.hotelbooking.web.dto.booking.response.BookingResponseDto;
import org.mapstruct.Mapper;

@Mapper(
        config = MapstructMapperConfiguration.class,
        uses = {
                RoomMapper.class, UserMapper.class
        }
)
public interface BookingMapper {
    Booking toDomain(BookingRequestDto request);
    Booking toDomain(BookingEntity entity);
    BookingEntity toEntity(Booking booking);
    BookingResponseDto toDto(Booking booking);
}