package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class AssetLifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;
    private String eventDescription;
    private LocalDate eventDate;

    @ManyToOne
    private Asset asset;

    public Long getId() { return id; }

    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
    public void setAsset(Asset asset) { this.asset = asset; }
}
