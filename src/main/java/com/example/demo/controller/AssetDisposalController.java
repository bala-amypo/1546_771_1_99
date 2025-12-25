package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(AssetDisposalService service) {
        this.service = service;
    }

    @PostMapping("/{assetId}/{userId}")
    public ResponseEntity<AssetDisposal> requestDisposal(
            @PathVariable Long assetId,
            @PathVariable Long userId,
            @RequestBody AssetDisposal disposal) {

        return ResponseEntity.ok(
                service.requestDisposal(assetId, userId, disposal)
        );
    }

    @PutMapping("/{disposalId}/approve/{adminId}")
    public ResponseEntity<AssetDisposal> approve(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        return ResponseEntity.ok(
                service.approveDisposal(disposalId, adminId)
        );
    }
}
