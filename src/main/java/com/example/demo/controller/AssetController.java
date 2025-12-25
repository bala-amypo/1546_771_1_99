package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.repository.VendorRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository ruleRepository;

    public AssetController(AssetRepository assetRepository, VendorRepository vendorRepository,
                          DepreciationRuleRepository ruleRepository) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
    }

    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<?> createAsset(@PathVariable Long vendorId, @PathVariable Long ruleId,
                                        @Valid @RequestBody Asset asset) {
        try {
            if (asset.getPurchaseCost() < 0) {
                return ResponseEntity.status(500).body("Purchase cost cannot be negative");
            }

            Vendor vendor = vendorRepository.findById(vendorId)
                    .orElseThrow(() -> new RuntimeException("Vendor not found"));
            DepreciationRule rule = ruleRepository.findById(ruleId)
                    .orElseThrow(() -> new RuntimeException("Rule not found"));

            asset.setVendor(vendor);
            asset.setDepreciationRule(rule);
            if (asset.getStatus() == null) {
                asset.setStatus("ACTIVE");
            }

            Asset saved = assetRepository.save(asset);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAllAssets() {
        return ResponseEntity.ok(assetRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAssetById(@PathVariable Long id) {
        return assetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Asset>> getAssetsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(assetRepository.findByStatus(status));
    }
}
