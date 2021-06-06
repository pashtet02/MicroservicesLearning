package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.vo.ResponseTemplateVO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    List<UserDto> getAllUsers(Pageable pageRequest);

    UserDto getUser(String username);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String username, UserDto userDto);

    void deleteUser(String username);

    ResponseTemplateVO getUserWithCards(Long userId);
}

