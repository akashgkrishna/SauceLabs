package org.bookcart.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private final String confirmPassword;
    private final String gender;
}
