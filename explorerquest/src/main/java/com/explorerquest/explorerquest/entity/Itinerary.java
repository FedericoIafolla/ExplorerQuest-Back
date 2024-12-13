package com.explorerquest.explorerquest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "itineraries")
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int totalDays;

    @ElementCollection
    @CollectionTable(name = "itinerary_days", joinColumns = @JoinColumn(name = "itinerary_id"))
    @Column(name = "description")
    private List<String> dayDescriptions;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL)
    private List<PointOfInterest> pointsOfInterest;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
