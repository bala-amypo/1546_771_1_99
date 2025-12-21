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

    // ================= POST =================
    @PostMapping
    public ResponseEntity<AssetDisposal> requestDisposal(
            @RequestBody AssetDisposal disposal) {

        AssetDisposal saved = service.requestDisposal(disposal);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(saved);
    }

    // ================= PUT =================
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        AssetDisposal approved =
                service.approveDisposal(disposalId, adminId);

        return ResponseEntity.ok(approved);
    }
}
