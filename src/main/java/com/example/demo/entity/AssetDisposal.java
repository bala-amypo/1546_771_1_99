package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "asset_disposal")
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;
    private String status;

    @Column(name = "asset_id")
    private Long assetId;

    @Column(name = "requested_by")
    private Long requestedBy;

    // getters & setters
}
