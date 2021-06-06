package com.example.userservice.controller;

import com.example.userservice.api.UserApi;
import com.example.userservice.controller.assembler.UserAssembler;
import com.example.userservice.controller.model.UserModel;
import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.ResponseTemplateVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Slf4j
public class UserController implements UserApi {
    private final UserService userService;
    private final UserAssembler userAssembler;

    //how to validate sortParam without creating hashSet with valid options???!
    @Override
    public List<UserModel> getAllUsers(Integer page, String sortParam, Integer numOfUsers) {
        log.info("Get all users, timestamp {}", LocalDateTime.now());
        Pageable pageRequest = PageRequest.of(page, numOfUsers, Sort.by(sortParam));
        return userService.getAllUsers(pageRequest).stream()
                .map(userAssembler::toModel)
                .collect(Collectors.toList());
    }

    /*@Override
    public UserModel getUser(String username) {
        log.info("Get user by username: {}", username);
        UserDto userDto = userService.getUser(username);
        return userAssembler.toModel(userDto);
    }*/

    /*@GetMapping("/{id}")
    @Override
    public ResponseTemplateVO getUserWithPayments(@PathVariable("id") Long userId){
        log.info("Get user with payments by id: {}", userId);
        return userService.getUserWithPayments(userId);
    }*/

    @GetMapping("/{id}")
    @Override
    public ResponseTemplateVO getUserWithCards(@PathVariable("id") Long userId){
        log.info("Get user with cards by id: {}", userId);
        return userService.getUserWithCards(userId);
    }

    @Override
    public UserModel createUser(UserDto userDto) {
        log.info("Create user: {}", userDto);
        UserDto user = userService.createUser(userDto);
        return userAssembler.toModel(user);
    }

    @Override
    public UserModel updateUser(String username, UserDto userDto) {
        log.info("Update user by username: {} user: {}", username, userDto);
        UserDto user = userService.updateUser(username, userDto);
        return userAssembler.toModel(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String username) {
        log.info("Delete user by username: {}", username);
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }
}
