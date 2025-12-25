package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
    name = "assets",
    uniqueConstraints = @UniqueConstraint(columnNames = "assetTag")
)
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String assetTag;

    private String assetName;

    private LocalDate purchaseDate;
    private Double purchaseCost;

    @Column(nullable = false)
    private String status = "ACTIVE";

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private DepreciationRule depreciationRule;

    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    private List<AssetLifecycleEvent> lifecycleEvents;

    @OneToOne(mappedBy = "asset", cascade = CascadeType.ALL)
    private AssetDisposal disposal;

    public Asset() {}

    public Asset(String assetTag, String assetName, LocalDate purchaseDate, Double purchaseCost) {
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.purchaseDate = purchaseDate;
        this.purchaseCost = purchaseCost;
    }

    // getters and setters
}
