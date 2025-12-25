package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lifecycle")
public class AssetLifecycleEventController {

    private final AssetLifecycleEventService service;

    public AssetLifecycleEventController(AssetLifecycleEventService service) {
        this.service = service;
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> add(
            @PathVariable Long assetId,
            @RequestBody AssetLifecycleEvent event) {

        return ResponseEntity.ok(
                service.logEvent(assetId, event)
        );
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getByAsset(
            @PathVariable Long assetId) {

        return ResponseEntity.ok(
                service.getByAssetId(assetId)
        );
    }
}
