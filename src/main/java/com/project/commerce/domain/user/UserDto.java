package com.project.commerce.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private String username;
    private String password;
    private String email;
    private Address address;
}
