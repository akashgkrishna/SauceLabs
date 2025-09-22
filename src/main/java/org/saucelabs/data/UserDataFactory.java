package org.saucelabs.data;

import org.saucelabs.model.User;

import java.util.UUID;

public class UserDataFactory {
    public static User defaultUser() {
        String password = "TestUser1";
        return User.builder()
                .firstName("Test")
                .lastName("User")
                .userName("user" + UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 4))
                .password(password)
                .confirmPassword(password)
                .gender("Male").build();
    }

    public static User invalidUserName() {
        String username = "test01"; //Already registered username
        String password = "TestUser1";
        return User.builder()
                .firstName("Test")
                .lastName("User")
                .userName(username)
                .password(password)
                .confirmPassword(password)
                .gender("Male").build();
    }

    public static User nonMatchingPasswords() {
        String password1 = "TestUser1";
        String password2 = "TestUser2";
        return User.builder()
                .firstName("Test")
                .lastName("User")
                .userName("user" + UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 4))
                .password(password1)
                .confirmPassword(password2)
                .gender("Male").build();
    }

    public static User passwordLessThanEightCharacters() {
        String password = "Test1";
        return User.builder()
                .firstName("Test")
                .lastName("User")
                .userName("user" + UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 4))
                .password(password)
                .confirmPassword(password)
                .gender("Male").build();
    }

    public static User passwordWithoutUpperCase() {
        String password = "testuser1";
        return User.builder()
                .firstName("Test")
                .lastName("User")
                .userName("user" + UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 4))
                .password(password)
                .confirmPassword(password)
                .gender("Male").build();
    }

    public static User passwordWithoutNumber() {
        String password = "TestUsers";
        return User.builder()
                .firstName("Test")
                .lastName("User")
                .userName("user" + UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 4))
                .password(password)
                .confirmPassword(password)
                .gender("Male").build();
    }

}
