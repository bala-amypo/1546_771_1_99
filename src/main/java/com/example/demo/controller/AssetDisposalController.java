package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
@CrossOrigin(origins = "*")
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(AssetDisposalService service) {
        this.service = service;
    }

    // POST /api/disposals/request/{assetId} - Request disposal
    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposal> requestDisposal(
            @PathVariable Long assetId,
            @Valid @RequestBody AssetDisposal disposal) {

        AssetDisposal saved = service.requestDisposal(assetId, disposal);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /api/disposals/approve/{disposalId}/{adminId} - Approve disposal
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        AssetDisposal approved = service.approveDisposal(disposalId, adminId);
        return ResponseEntity.ok(approved);
    }
}