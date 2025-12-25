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
    public ResponseEntity<AssetLifecycleEvent> addEvent(
            @PathVariable Long assetId,
            @RequestBody AssetLifecycleEvent event) {

        return ResponseEntity.ok(service.addEvent(assetId, event));
    }

    @GetMapping("/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getEvents(
            @PathVariable Long assetId) {

        return ResponseEntity.ok(service.getEventsByAsset(assetId));
    }
}
