package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);

    @InheritInverseConfiguration
    UserDto toUserDto(User user);
}
