package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
public class AssetLifecycleEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String eventType;

    @NotBlank
    private String eventDescription;

    @PastOrPresent
    private LocalDate eventDate;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;
}