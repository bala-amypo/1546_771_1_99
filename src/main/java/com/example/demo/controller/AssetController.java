package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
@CrossOrigin
public class AssetController {

    private final AssetService service;

    public AssetController(AssetService service) {
        this.service = service;
    }

    
    @PostMapping("/vendor/{vendorId}/rule/{ruleId}")
    public ResponseEntity<Asset> createAsset(
            @PathVariable Long vendorId,
            @PathVariable Long ruleId,
            @RequestBody Asset asset) {

        Asset savedAsset = service.createAsset(vendorId, ruleId, asset);

        
        return new ResponseEntity<>(savedAsset, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Asset> getAllAssets() {
        return service.getAllAssets();
    }

    @GetMapping("/{id}")
    public Asset getAsset(@PathVariable Long id) {
        return service.getAsset(id);
    }

    
    @GetMapping("/status/{status}")
    public List<Asset> getByStatus(@PathVariable String status) {
        return service.getAssetsByStatus(status);
    }
}
