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

    // ✅ POST – Request disposal
    @PostMapping("/request/{assetId}")
    public ResponseEntity<String> requestDisposal(
            @PathVariable Long assetId,
            @RequestBody AssetDisposalRequest request) {

        AssetDisposal disposal = new AssetDisposal();
        disposal.setDisposalMethod(request.getDisposalMethod());
        disposal.setDisposalValue(request.getDisposalValue());
        disposal.setDisposalDate(request.getDisposalDate());

        disposalService.requestDisposal(assetId, disposal);

        return ResponseEntity.ok("Asset disposal request created successfully");
    }

    // ✅ PUT – Approve disposal
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<String> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        disposalService.approveDisposal(disposalId, adminId);

        return ResponseEntity.ok("Asset disposal approved successfully");
    }
}
