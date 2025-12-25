package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class AssetLifecycleEventController {

    @Autowired
    private AssetLifecycleEventService eventService;

    @PostMapping("/{assetId}")
    public ResponseEntity<?> logEvent(
            @PathVariable Long assetId,
            @RequestBody AssetLifecycleEvent event) {
        try {
            return ResponseEntity.ok(eventService.logEvent(assetId, event));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<AssetLifecycleEvent>> getEventsForAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(eventService.getEventsForAsset(assetId));
    }
}