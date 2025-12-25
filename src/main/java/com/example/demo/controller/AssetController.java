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
    public ResponseEntity<?> createAsset(
            @RequestBody Asset asset,
            @PathVariable Long vendorId,
            @PathVariable Long ruleId) {
        try {
            return ResponseEntity.ok(assetService.createAsset(asset, vendorId, ruleId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssetById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(assetService.getAssetById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Asset>> getAssetsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(assetService.getAssetsByStatus(status));
    }
}