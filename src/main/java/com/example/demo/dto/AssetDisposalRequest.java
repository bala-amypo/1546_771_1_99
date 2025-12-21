package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Schema(name = "AssetDisposalCreateRequest")
public class AssetDisposalRequest {

    @NotNull(message = "disposalMethod is required")
    private String disposalMethod;

    @DecimalMin(value = "0.0", message = "disposalValue must be >= 0")
    private Double disposalValue;

    // Getters and Setters
    public String getDisposalMethod() { return disposalMethod; }
    public void setDisposalMethod(String disposalMethod) { this.disposalMethod = disposalMethod; }

    public Double getDisposalValue() { return disposalValue; }
    public void setDisposalValue(Double disposalValue) { this.disposalValue = disposalValue; }
}