package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asset-disposals")
@CrossOrigin
public class AssetDisposalController {

    private final AssetDisposalService disposalService;

    public AssetDisposalController(AssetDisposalService disposalService) {
        this.disposalService = disposalService;
    }

    // CREATE / REQUEST DISPOSAL
    @PostMapping("/{assetId}/{userId}")
    public AssetDisposal disposeAsset(@PathVariable Long assetId,
                                      @PathVariable Long userId,
                                      @RequestBody AssetDisposal disposal) {
        return disposalService.disposeAsset(assetId, userId, disposal);
    }

    // ✅ APPROVE DISPOSAL (ADMIN)
    @PutMapping("/approve/{disposalId}/{adminId}")
    public AssetDisposal approveDisposal(@PathVariable Long disposalId,
                                         @PathVariable Long adminId) {
        return disposalService.approveDisposal(disposalId, adminId);
    }
}