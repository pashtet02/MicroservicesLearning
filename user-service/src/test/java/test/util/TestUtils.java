package test.util;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;

public class TestUtils {
    public static final String TEST_USERNAME = "username";
    public static final String TEST_EMAIL = "mail@mail.com";
    public static final String TEST_FIRST_NAME = "firstName";
    public static final String TEST_LAST_NAME = "secondName";
    public static final String TEST_Password = "pwd";
    public static final String TEST_PHONE_NUMBER = "+380970310828";

    public static User createUser() {
        return User.builder()
                .email(TEST_EMAIL)
                .password(TEST_Password)
                .username(TEST_USERNAME)
                .phoneNumber(TEST_PHONE_NUMBER)
                .firstName(TEST_FIRST_NAME)
                .lastName(TEST_LAST_NAME)
                .enabled(true)
                .locked(false)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .email(TEST_EMAIL)
                .password(TEST_Password)
                .phoneNumber(TEST_PHONE_NUMBER)
                .username(TEST_USERNAME)
                .firstName(TEST_FIRST_NAME)
                .lastName(TEST_LAST_NAME)
                .build();
    }
}
