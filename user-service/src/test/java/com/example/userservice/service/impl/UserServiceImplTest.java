package com.example.userservice.service.impl;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static test.util.TestUtils.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void getAllUsers() {

    }

    @Test
    void testGetAllUsers() {
    }

    @Test
    void getUserTest() {
        User user = createUser();
        when(userRepository.findByUsername(TEST_USERNAME)).thenReturn(Optional.of(user));
        UserDto userDto = userService.getUser(TEST_USERNAME);

        assertThat(userDto, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("firstName", equalTo(user.getFirstName()))
        ));
    }

    @Test
    void createUserTest() {
        /*UserDto userDto = createUserDto();
        User user = createUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        when(userRepository.save(user)).thenReturn(user);
        //UserDto userDto = createUserDto();

        userDto = userService.createUser(userDto);

        assertThat(userDto, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("firstName", equalTo(user.getFirstName())),
                hasProperty("password", startsWith("$2a$"))
        ));*/
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserWithCards() {
    }
}