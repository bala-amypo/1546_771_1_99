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

    // ✅ MATCHES YOUR CURL
    @PostMapping
    public ResponseEntity<AssetDisposal> requestDisposal(
            @RequestBody AssetDisposal disposal) {

        AssetDisposal saved = service.requestDisposal(disposal);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ✅ PUT approve
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        return ResponseEntity.ok(
                service.approveDisposal(disposalId, adminId)
        );
    }
}
