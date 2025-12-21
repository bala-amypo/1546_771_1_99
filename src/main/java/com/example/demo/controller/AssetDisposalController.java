// src/main/java/com/example/demo/controller/AssetDisposalController.java
package com.example.demo.controller;

import com.example.demo.dto.AssetDisposalRequest;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import jakarta.validation.Valid;
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

    // POST — Request Disposal
    @PostMapping
    public ResponseEntity<AssetDisposal> requestDisposal(
            @Valid @RequestBody AssetDisposalRequest requestDto) {

        AssetDisposal disposal = new AssetDisposal();
        disposal.setReason(requestDto.getReason());
        disposal.setAssetId(requestDto.getAssetId());
        disposal.setRequestedBy(requestDto.getRequestedBy());
        // status and approvedBy are set in service

        AssetDisposal saved = service.requestDisposal(disposal);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT — Approve Disposal
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        AssetDisposal approved = service.approveDisposal(disposalId, adminId);
        return ResponseEntity.ok(approved);
    }
}