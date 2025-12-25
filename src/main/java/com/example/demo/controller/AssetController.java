package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    private AssetService assetService;

    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<Asset> createAsset(@RequestBody Asset asset, @PathVariable Long vendorId, @PathVariable Long ruleId) {
        return ResponseEntity.ok(assetService.createAsset(asset, vendorId, ruleId));
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAssetById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Asset>> getAssetsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(assetService.getAssetsByStatus(status));
    }

    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<Asset>> getAssetsByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(assetService.getAssetsByVendor(vendorId));
    }
}
