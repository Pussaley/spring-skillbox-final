package com.example.hotelbooking.mapper;

import com.example.hotelbooking.config.mapper.MapstructMapperConfiguration;
import com.example.hotelbooking.domain.Hotel;
import com.example.hotelbooking.domain.Room;
import com.example.hotelbooking.entity.RoomEntity;
import com.example.hotelbooking.service.HotelService;
import com.example.hotelbooking.web.dto.room.create.CreateRoomRequestDto;
import com.example.hotelbooking.web.dto.room.response.RoomResponseDto;
import com.example.hotelbooking.web.dto.room.update.UpdateRoomRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        config = MapstructMapperConfiguration.class,
        uses = {
                HotelService.class,
                HotelMapper.class
        }
)
public interface RoomMapper {
    Room toDomain(CreateRoomRequestDto request);
    Room toDomain(UpdateRoomRequestDto request);

    @Mapping(target = "hotelId", source = "entity.hotel.id")
    Room toDomain(RoomEntity entity);
    RoomEntity toEntity(Room room);
    RoomResponseDto toDto(Room room);

    @Mapping(target = "hotel", source = "hotel.id")
    @Mapping(target = "id", source = "room.id")
    @Mapping(target = "name", source = "room.name")
    RoomEntity toEntity(Room room, Hotel hotel);
}