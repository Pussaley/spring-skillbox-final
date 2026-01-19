package com.example.hotelbooking.mapper;

import com.example.hotelbooking.config.mapper.MapstructMapperConfiguration;
import com.example.hotelbooking.domain.User;
import com.example.hotelbooking.entity.UserEntity;
import com.example.hotelbooking.web.dto.user.create.UserRequestDto;
import com.example.hotelbooking.web.dto.user.response.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(
        config = MapstructMapperConfiguration.class
)
public interface UserMapper {
    User toDomain(UserRequestDto request);
    User toDomain(UserEntity entity);
    UserEntity toEntity(User user);
    UserResponseDto toDto(User user);
}