package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
@Tag(name = "Asset Disposal Controller", description = "Endpoints for asset disposal requests and approvals")
public class AssetDisposalController {

    @Autowired
    private AssetDisposalService disposalService;

    @PostMapping("/request/{assetId}")
    public AssetDisposal requestDisposal(@PathVariable Long assetId, 
                                         @RequestBody AssetDisposal disposal) {
        return disposalService.requestDisposal(assetId, disposal);
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public AssetDisposal approveDisposal(@PathVariable Long disposalId, 
                                         @PathVariable Long adminId) {
        return disposalService.approveDisposal(disposalId, adminId);
    }
}