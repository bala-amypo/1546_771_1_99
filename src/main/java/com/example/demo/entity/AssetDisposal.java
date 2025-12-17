package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class AssetDisposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    private String status;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne
    @JoinColumn(name = "requested_by")
    private User requestedBy;

    public AssetDisposal() {}

    public Long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public Asset getAsset() {
        return asset;
    }

    public User getRequestedBy() {
        return requestedBy;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public void setRequestedBy(User requestedBy) {
        this.requestedBy = requestedBy;
    }
}
