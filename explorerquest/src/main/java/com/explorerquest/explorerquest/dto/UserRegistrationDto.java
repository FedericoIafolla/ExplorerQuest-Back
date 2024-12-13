package com.explorerquest.explorerquest.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profileImageUrl;
}
