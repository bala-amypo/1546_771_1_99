package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.entity.Vendor;
import com.example.demo.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetRepository assetRepository;
    private final VendorRepository vendorRepository;
    private final DepreciationRuleRepository ruleRepository;

    public AssetController(AssetRepository assetRepository,
                           VendorRepository vendorRepository,
                           DepreciationRuleRepository ruleRepository) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
    }

    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<Asset> create(@PathVariable Long vendorId,
                                        @PathVariable Long ruleId,
                                        @RequestBody Asset asset) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();
        DepreciationRule rule = ruleRepository.findById(ruleId).orElseThrow();
        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        return ResponseEntity.ok(assetRepository.save(asset));
    }

    @GetMapping
    public ResponseEntity<List<Asset>> getAll() {
        return ResponseEntity.ok(assetRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getById(@PathVariable Long id) {
        return assetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Asset>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(assetRepository.findByStatus(status));
    }
}