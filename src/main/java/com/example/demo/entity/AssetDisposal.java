package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "asset_disposals")
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "asset_id", unique = true)
    private Asset asset;

    private String disposalMethod;
    private Double disposalValue;
    private LocalDate disposalDate;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    public AssetDisposal() {}

    public AssetDisposal(Asset asset, String disposalMethod, Double disposalValue,
                         LocalDate disposalDate, User approvedBy) {
        this.asset = asset;
        this.disposalMethod = disposalMethod;
        this.disposalValue = disposalValue;
        this.disposalDate = disposalDate;
        this.approvedBy = approvedBy;
    }

    // getters and setters
}
