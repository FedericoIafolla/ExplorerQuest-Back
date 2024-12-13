package com.explorerquest.explorerquest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileUpdateDto {
    private String firstName; // Optional
    private String lastName; // Optional
    private String bio; // Optional
    private String profileImageUrl; // Optional
    private LocalDate dateOfBirth; // Optional
}
