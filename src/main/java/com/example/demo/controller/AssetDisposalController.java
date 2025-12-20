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

    // ✅ POST – 201 CREATED
    @PostMapping
    public ResponseEntity<?> requestDisposal(@RequestBody AssetDisposal disposal) {
        try {
            AssetDisposal saved = service.requestDisposal(disposal);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // ✅ PUT – APPROVE
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<?> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        try {
            AssetDisposal approved = service.approveDisposal(disposalId, adminId);
            return ResponseEntity.ok(approved);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}
