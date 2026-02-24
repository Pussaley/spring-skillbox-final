package com.example.hotelbooking.domain.mapper;

import com.example.hotelbooking.config.mapper.MapstructMapperConfiguration;
import com.example.hotelbooking.domain.dto.Booking;
import com.example.hotelbooking.domain.entity.BookingEntity;
import com.example.hotelbooking.domain.service.domain.RoomService;
import com.example.hotelbooking.domain.service.domain.UserService;
import com.example.hotelbooking.web.dto.booking.create.BookingRequestDto;
import com.example.hotelbooking.web.dto.booking.response.BookingResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MapstructMapperConfiguration.class,
        uses = {
                RoomMapper.class,
                RoomService.class,
                UserMapper.class,
                UserService.class
        }
)
public interface BookingMapper {

    @Mapping(target = "checkInDate", source = "checkIn")
    @Mapping(target = "checkOutDate", source = "checkOut")
    @Mapping(target = "roomId", source = "roomId")
    Booking toDomain(BookingRequestDto request);

    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "questId", source = "quest.id")
    Booking toDomain(BookingEntity entity);

    @Mapping(target = "room", source = "roomId")
    @Mapping(target = "quest", source = "questId")
    BookingEntity toEntity(Booking booking);
    @Mapping(target = "roomId", source = "roomId")
    @Mapping(target = "questId", source = "questId")
    BookingResponseDto toDto(Booking booking);
}