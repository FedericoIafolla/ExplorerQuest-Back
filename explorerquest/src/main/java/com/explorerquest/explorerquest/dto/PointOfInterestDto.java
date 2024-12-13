package com.explorerquest.explorerquest.dto;

import lombok.Data;

@Data
public class PointOfInterestDto {
    private String name;
    private String description;
    private double latitude;
    private double longitude;
}
