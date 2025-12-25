package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_lifecycle_events")
public class AssetLifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private String eventDescription;

    private LocalDate eventDate;
    private LocalDateTime loggedAt = LocalDateTime.now();

    public AssetLifecycleEvent() {}

    public AssetLifecycleEvent(Asset asset, String eventType, String eventDescription, LocalDate eventDate) {
        this.asset = asset;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
    }

    // getters and setters
}
