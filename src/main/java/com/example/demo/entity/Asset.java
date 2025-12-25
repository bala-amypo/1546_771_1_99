package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetTag;       // getAssetTag()

    private double purchaseCost;   // getPurchaseCost()

    private String status;          // setStatus()

    private LocalDateTime createdAt; // setCreatedAt()

    @ManyToOne
    private Vendor vendor;          // setVendor()

    @ManyToOne
    private DepreciationRule depreciationRule; // setDepreciationRule()
}
