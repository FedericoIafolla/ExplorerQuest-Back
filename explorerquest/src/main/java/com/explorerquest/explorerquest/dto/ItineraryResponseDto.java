package com.explorerquest.explorerquest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItineraryResponseDto {
    private String name;
    private int totalDays;
    private List<String> dayDescriptions;
    private List<PointOfInterestDto> pointsOfInterest;
}
