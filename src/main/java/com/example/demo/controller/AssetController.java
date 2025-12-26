package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.service.AssetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assets")
public class AssetController {

    private final AssetService service;

    public AssetController(AssetService service) {
        this.service = service;
    }

    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return service.save(asset);
    }

    @GetMapping
    public List<Asset> getAllAssets() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Asset getAssetById(@PathVariable Long id) {
        return service.getById(id);
    }
}
