package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.Vendor;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.VendorRepository;
import com.example.demo.repository.DepreciationRuleRepository;
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

    public AssetController(AssetRepository assetRepository,
                           VendorRepository vendorRepository,
                           DepreciationRuleRepository ruleRepository) {
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
        this.ruleRepository = ruleRepository;
    }

    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<?> create(@PathVariable Long vendorId,
                                    @PathVariable Long ruleId,
                                    @Valid @RequestBody Asset asset) {
        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
        DepreciationRule rule = ruleRepository.findById(ruleId).orElse(null);
        if (vendor == null || rule == null) {
            return ResponseEntity.badRequest().build();
        }
        asset.setVendor(vendor);
        asset.setDepreciationRule(rule);
        return ResponseEntity.ok(assetRepository.save(asset));
    }

    @GetMapping
    public List<Asset> getAll() {
        return assetRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getById(@PathVariable Long id) {
        return assetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public List<Asset> getByStatus(@PathVariable String status) {
        return assetRepository.findByStatus(status);
    }
}