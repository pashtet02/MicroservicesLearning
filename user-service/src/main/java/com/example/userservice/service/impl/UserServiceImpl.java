package com.example.userservice.service.impl;

import com.example.userservice.dto.UserDto;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Card;
import com.example.userservice.vo.ResponseTemplateVO;
import com.example.userservice.vo.SmsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;

    @Override
    public List<UserDto> getAllUsers(Pageable pageRequest) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userRepository.findAll(pageRequest)) {
            userDtoList.add(UserMapper.INSTANCE.toUserDto(user));
        }
        log.info("getAllUsers, numberOf users: {}", userDtoList.size());
        return userDtoList;
    }

    //method to get list of all users in info endpoint
    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            userDtoList.add(UserMapper.INSTANCE.toUserDto(user));
        }
        log.info("getAllUsers, numberOf users: {}", userDtoList.size());
        return userDtoList;
    }

    @Override
    public UserDto getUser(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        log.debug("getUser() by username {}", username);
        return UserMapper.INSTANCE.toUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        var user = UserMapper.INSTANCE.toUser(userDto);
        user.setId(0);

        String encryptedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPass);
        user = userRepository.save(user);
        log.debug("createUser() from {}", userDto);
        return UserMapper.INSTANCE.toUserDto(user);
    }

    @Override
    public UserDto updateUser(String username, UserDto userDto) {
        var user = UserMapper.INSTANCE.toUser(userDto);
        if (!userRepository.existsByUsername(username)) {
            throw new UserNotFoundException();
        }
        Optional<User> userFromDB = userRepository.findByUsername(username);
        if (userFromDB.isPresent()){
            user.setId(userFromDB.get().getId());
        }
        user = userRepository.save(user);
        log.debug("updateUser() by username {}, from dto: {}", username, userDto);
        return UserMapper.INSTANCE.toUserDto(user);
    }

    @Override
    public void deleteUser(String username) {
        log.debug("deleteUser() by username {}", username);
        var user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    @Override
    public ResponseTemplateVO getUserWithCards(Long userId) {
        var vo = new ResponseTemplateVO();
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        log.info("get User from db by id: {}", user);

        List<Card> cards = restTemplate.getForObject("http://CARDSERVICE/api/v1/cards/" + userId, List.class);

        vo.setUser(user);
        vo.setCards(cards);

        return vo;
    }
}
