package com.explorerquest.explorerquest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String usernameOrEmail; // Cambiato da "username" a "usernameOrEmail"
    private String password;
}
