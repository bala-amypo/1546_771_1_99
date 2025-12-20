package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asset-disposals")
@CrossOrigin
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(AssetDisposalService service) {
        this.service = service;
    }

    // ===============================
    // POST /api/asset-disposals
    // Request asset disposal (201)
    // ===============================
    @PostMapping
    public ResponseEntity<AssetDisposal> requestDisposal(
            @RequestBody AssetDisposal disposal) {

        disposal.setStatus("PENDING");

        AssetDisposal saved = service.requestDisposal(
                disposal.getAssetId(), disposal);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ===============================
    // PUT /api/asset-disposals/approve/{disposalId}/{adminId}
    // Approve disposal
    // ===============================
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        AssetDisposal approved = service.approveDisposal(disposalId, adminId);
        return ResponseEntity.ok(approved);
    }
}
