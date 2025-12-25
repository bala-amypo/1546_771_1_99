package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssetLifecycleEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private String eventDescription;

    private LocalDate eventDate;

    @ManyToOne
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;
}
