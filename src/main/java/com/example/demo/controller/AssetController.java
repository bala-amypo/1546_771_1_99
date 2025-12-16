package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    // CREATE ASSET
    @PostMapping("/{vendorId}/{ruleId}")
    public Asset createAsset(@PathVariable Long vendorId,
                             @PathVariable Long ruleId,
                             @RequestBody Asset asset) {
        return assetService.createAsset(vendorId, ruleId, asset);
    }

    // GET ALL ASSETS
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetService.getAllAssets();
    }

    // GET ASSET BY ID
    @GetMapping("/{id}")
    public Asset getAssetById(@PathVariable Long id) {
        return assetService.getAsset(id);
    }

    // ✅ GET ASSETS BY STATUS
    @GetMapping("/status/{status}")
    public List<Asset> getAssetsByStatus(@PathVariable String status) {
        return assetService.getAssetsByStatus(status);
    }
}