package com.example.demo.controller;

import com.example.demo.entity.AssetLifecycleEvent;
import com.example.demo.service.AssetLifecycleEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lifecycle-events")
@CrossOrigin
public class AssetLifecycleEventController {

    private final AssetLifecycleEventService service;

    public AssetLifecycleEventController(AssetLifecycleEventService service) {
        this.service = service;
    }

    // POST /lifecycle-events/asset/{assetId}
    @PostMapping("/asset/{assetId}")
    public ResponseEntity<AssetLifecycleEvent> logEvent(
            @PathVariable Long assetId,
            @RequestBody AssetLifecycleEvent event) {

        AssetLifecycleEvent savedEvent = service.logEvent(assetId, event);

        // ✅ Return 201 CREATED
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }


    @GetMapping("/asset/{assetId}")
    public List<AssetLifecycleEvent> getEventsByAsset(@PathVariable Long assetId) {
        return service.getEventsByAsset(assetId);
    }
}
