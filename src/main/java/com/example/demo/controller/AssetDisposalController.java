package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    @Autowired
    private AssetDisposalService disposalService;

    @PostMapping("/request/{assetId}")
    public ResponseEntity<?> requestDisposal(
            @PathVariable Long assetId,
            @RequestBody AssetDisposal disposal) {
        try {
            return ResponseEntity.ok(disposalService.requestDisposal(assetId, disposal));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PutMapping("/approve/{disposalId}/{approverId}")
    public ResponseEntity<?> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long approverId) {
        try {
            return ResponseEntity.ok(disposalService.approveDisposal(disposalId, approverId));
        } catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<AssetDisposal>> getAllDisposals() {
        return ResponseEntity.ok(disposalService.getAllDisposals());
    }
}