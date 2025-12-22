package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;  // ← Add this import
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AssetDisposalCreateRequest")
@JsonIgnoreProperties(ignoreUnknown = true)  // ← Add this line — ignores extra fields
public class AssetDisposalRequest {

    private String disposalMethod;
    private Double disposalValue;

    // Getters and Setters unchanged
    public String getDisposalMethod() { return disposalMethod; }
    public void setDisposalMethod(String disposalMethod) { this.disposalMethod = disposalMethod; }

    public Double getDisposalValue() { return disposalValue; }
    public void setDisposalValue(Double disposalValue) { this.disposalValue = disposalValue; }
}