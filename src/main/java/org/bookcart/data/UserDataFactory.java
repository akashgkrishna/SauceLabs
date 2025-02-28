package org.bookcart.data;

import org.bookcart.model.User;

import java.util.UUID;

public class UserDataFactory {
    public static User defaultUser(){
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
}
