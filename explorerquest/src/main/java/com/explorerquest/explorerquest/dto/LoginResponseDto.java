package com.explorerquest.explorerquest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String token; // Contiene il token JWT
}
