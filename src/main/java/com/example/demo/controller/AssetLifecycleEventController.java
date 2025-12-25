package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset-events")
public class AssetLifecycleEventController {

    @Autowired
    private AssetLifecycleEventService service;

    @PostMapping("/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> addEvent(
            @PathVariable Long assetId,
            @RequestBody AssetLifecycleEvent event) {
        return ResponseEntity.ok(service.addEvent(assetId, event));
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getEventsByAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(service.getEventsByAsset(assetId));
    }
}
