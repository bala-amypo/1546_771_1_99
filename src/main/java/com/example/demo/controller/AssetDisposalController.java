package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AssetDisposalRequest;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    @Autowired
    private AssetDisposalService disposalService;

    // ✅ Request asset disposal (FIXED)
    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposal> requestDisposal(
            @PathVariable Long assetId,
            @RequestBody AssetDisposalRequest request) {

        AssetDisposal disposal = new AssetDisposal();
        disposal.setDisposalMethod(request.getDisposalMethod());
        disposal.setDisposalValue(request.getDisposalValue());
        disposal.setDisposalDate(request.getDisposalDate());

        return ResponseEntity.ok(
                disposalService.requestDisposal(assetId, disposal)
        );
    }

    // ✅ Approve disposal (NO CHANGE NEEDED)
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        return ResponseEntity.ok(
                disposalService.approveDisposal(disposalId, adminId)
        );
    }
}
