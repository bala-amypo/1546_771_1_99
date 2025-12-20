package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals") // 🔥 THIS MUST MATCH URL
@CrossOrigin
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(AssetDisposalService service) {
        this.service = service;
    }

    // ✅ POST → 201 CREATED
    @PostMapping("/request/{assetId}")
    public ResponseEntity<?> requestDisposal(
            @PathVariable Long assetId,
            @RequestBody AssetDisposal disposal) {

        try {
            AssetDisposal saved = service.requestDisposal(assetId, disposal);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // ✅ PUT approve
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        return ResponseEntity.ok(service.approveDisposal(disposalId, adminId));
    }
}
