package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAssets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.getAssetById(id));
    }

    @PostMapping("/{vendorId}/{depreciationRuleId}")
    public ResponseEntity<Asset> createAsset(@PathVariable Long vendorId,
                                             @PathVariable Long depreciationRuleId,
                                             @RequestBody Asset asset) {
        return ResponseEntity.ok(assetService.createAsset(vendorId, depreciationRuleId, asset));
    }
}
