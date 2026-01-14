package com.example.hotelbooking.mapper;

import com.example.hotelbooking.config.mapper.MapstructMapperConfiguration;
import com.example.hotelbooking.domain.Room;
import com.example.hotelbooking.entity.RoomEntity;
import com.example.hotelbooking.web.dto.request.RoomRequestDto;
import com.example.hotelbooking.web.dto.response.RoomResponseDto;
import org.mapstruct.Mapper;

@Mapper(
        config = MapstructMapperConfiguration.class
)
public interface RoomMapper {
    Room toDomain(RoomRequestDto request);
    Room toDomain(RoomEntity entity);
    RoomEntity toEntity(Room room);
    RoomResponseDto toDto(Room room);
}