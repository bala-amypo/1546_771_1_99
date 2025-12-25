package com.example.demo.controller;

import com.example.demo.entity.Asset;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.entity.User;
import com.example.demo.service.AssetDisposalService;
import com.example.demo.service.AssetService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disposals")
public class AssetDisposalController {

    private final AssetDisposalService disposalService;
    private final AssetService assetService;
    private final UserService userService;

    public AssetDisposalController(AssetDisposalService disposalService,
                                   AssetService assetService,
                                   UserService userService) {
        this.disposalService = disposalService;
        this.assetService = assetService;
        this.userService = userService;
    }

    /**
     * POST /api/disposals/request/{assetId}
     * Anyone (authenticated) can request disposal of an asset
     */
    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposal> requestDisposal(
            @PathVariable Long assetId,
            @Valid @RequestBody AssetDisposal disposalRequest) {

        Asset asset = assetService.findById(assetId);
        disposalRequest.setAsset(asset);

        AssetDisposal saved = disposalService.requestDisposal(disposalRequest);
        return ResponseEntity.ok(saved);
    }

    /**
     * PUT /api/disposals/approve/{disposalId}/{userId}
     * Only ADMIN can approve (enforced by security config or service logic)
     */
    @PutMapping("/approve/{disposalId}/{userId}")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long userId) {

        User approvedBy = userService.findById(userId);

        AssetDisposal approved = disposalService.approveDisposal(disposalId, approvedBy);
        return ResponseEntity.ok(approved);
    }
}