package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asset-disposal")
public class AssetDisposalController {

    private final AssetDisposalService assetDisposalService;

    public AssetDisposalController(AssetDisposalService assetDisposalService) {
        this.assetDisposalService = assetDisposalService;
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<AssetDisposal> requestDisposal(
            @PathVariable Long assetId,
            @RequestBody AssetDisposal disposal) {

        return ResponseEntity.ok(
                assetDisposalService.requestDisposal(assetId, disposal)
        );
    }

    @PutMapping("/{disposalId}/approve")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId) {

        return ResponseEntity.ok(
                assetDisposalService.approveDisposal(disposalId)
        );
    }
}
