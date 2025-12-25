package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.repository.AssetDisposalRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalRepository disposalRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetDisposalController(AssetDisposalRepository disposalRepository,
                                  AssetRepository assetRepository,
                                  UserRepository userRepository) {
        this.disposalRepository = disposalRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/request/{assetId}")
    public ResponseEntity<?> requestDisposal(@PathVariable Long assetId,
                                            @RequestBody AssetDisposal disposal) {
        try {
            Asset asset = assetRepository.findById(assetId)
                    .orElseThrow(() -> new RuntimeException("Asset not found"));

            disposal.setAsset(asset);
            AssetDisposal saved = disposalRepository.save(disposal);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/approve/{disposalId}/{approverId}")
    public ResponseEntity<?> approveDisposal(@PathVariable Long disposalId,
                                            @PathVariable Long approverId) {
        try {
            AssetDisposal disposal = disposalRepository.findById(disposalId)
                    .orElseThrow(() -> new RuntimeException("Disposal not found"));
            
            User approver = userRepository.findById(approverId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            disposal.setApprovedBy(approver);
            AssetDisposal updated = disposalRepository.save(disposal);

            // Update asset status
            Asset asset = disposal.getAsset();
            asset.setStatus("DISPOSED");
            assetRepository.save(asset);

            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}