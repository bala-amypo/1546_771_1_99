package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.repository.AssetRepository;
import com.example.demo.service.AssetService;
import com.example.demo.service.DepreciationRuleService;
import com.example.demo.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;
    private final VendorService vendorService;
    private final DepreciationRuleService ruleService;
    private final AssetRepository assetRepository;

    public AssetController(AssetService assetService,
                           VendorService vendorService,
                           DepreciationRuleService ruleService,
                           AssetRepository assetRepository) {
        this.assetService = assetService;
        this.vendorService = vendorService;
        this.ruleService = ruleService;
        this.assetRepository = assetRepository;
    }

    @PostMapping("/{vendorId}/{ruleId}")
    public ResponseEntity<?> create(@PathVariable Long vendorId,
                                    @PathVariable Long ruleId,
                                    @Valid @RequestBody Asset asset) {
        try {
            var vendor = vendorService.findById(vendorId);
            var rule = ruleService.findById(ruleId);
            Asset saved = assetService.create(asset, vendor, rule);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
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
    public ResponseEntity<List<Asset>> getByStatus(@PathVariable String status) {
        List<Asset> assets = assetRepository.findByStatus(status);
        return ResponseEntity.ok(assets); // Returns empty list if none
    }
}