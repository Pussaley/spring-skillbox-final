package com.example.hotelbooking.mapper;

import com.example.hotelbooking.config.mapper.MapstructMapperConfiguration;
import com.example.hotelbooking.domain.Room;
import com.example.hotelbooking.entity.RoomEntity;
import com.example.hotelbooking.web.dto.room.create.CreateRoomRequestDto;
import com.example.hotelbooking.web.dto.room.response.RoomResponseDto;
import com.example.hotelbooking.web.dto.room.update.UpdateRoomRequestDto;
import org.mapstruct.Mapper;

@Mapper(
        config = MapstructMapperConfiguration.class
)
public interface RoomMapper {
    Room toDomain(CreateRoomRequestDto request);
    Room toDomain(UpdateRoomRequestDto request);
    Room toDomain(RoomEntity entity);
    RoomEntity toEntity(Room room);
    RoomResponseDto toDto(Room room);
}