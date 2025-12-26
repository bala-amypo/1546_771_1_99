package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@Tag(name = "Asset Controller", description = "Endpoints for managing enterprise assets")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/{vendorId}/{ruleId}")
    @Operation(summary = "Create a new asset linked to a vendor and rule")
    public Asset createAsset(@PathVariable Long vendorId, 
                             @PathVariable Long ruleId, 
                             @RequestBody Asset asset) {
        return assetService.createAsset(vendorId, ruleId, asset);
    }

    @GetMapping
    @Operation(summary = "List all assets")
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "List assets by status (ACTIVE, MAINTENANCE, DISPOSED)")
    public List<Asset> getAssetsByStatus(@PathVariable String status) {
        return assetService.getAssetsByStatus(status);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a specific asset by ID")
    public Asset getAsset(@PathVariable Long id) {
        return assetService.getAsset(id);
    }
}