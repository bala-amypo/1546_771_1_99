package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<Asset> createAsset(
            @PathVariable Long vendorId,
            @PathVariable Long ruleId,
            @RequestBody Asset asset) {

        return ResponseEntity.ok(
                assetService.createAsset(vendorId, ruleId, asset)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getById(@PathVariable Long id) {
        return ResponseEntity.ok(assetService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAll() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }
}
