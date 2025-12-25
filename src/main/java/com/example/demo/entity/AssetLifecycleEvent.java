package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetLifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;          // getEventType()

    private String eventDescription;   // getEventDescription()

    private LocalDate eventDate;       // getEventDate()

    private LocalDateTime loggedAt;    // setLoggedAt()

    @ManyToOne
    private Asset asset;               // setAsset()
}
