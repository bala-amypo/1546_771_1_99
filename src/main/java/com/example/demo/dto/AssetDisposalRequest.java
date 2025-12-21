// src/main/java/com/example/demo/dto/AssetDisposalRequest.java
package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

public class AssetDisposalRequest {

    private String reason;

    @NotNull(message = "assetId is required")
    private Long assetId;

    private Long requestedBy;

    // Getters and Setters
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Long getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Long requestedBy) {
        this.requestedBy = requestedBy;
    }
}