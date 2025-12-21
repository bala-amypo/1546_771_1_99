package com.example.demo.controller;

import com.example.demo.dto.AssetDisposalRequest;
import com.example.demo.dto.AssetDisposalResponse;
import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<AssetDisposalResponse> requestDisposal(
            @Valid @RequestBody AssetDisposalRequest requestDto) {

        AssetDisposal disposal = new AssetDisposal();
        disposal.setReason(requestDto.getReason());
        disposal.setAssetId(requestDto.getAssetId());
        disposal.setRequestedBy(requestDto.getRequestedBy());

        AssetDisposalResponse response = service.requestDisposal(disposal);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposalResponse> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        AssetDisposalResponse response = service.approveDisposal(disposalId, adminId);
        return ResponseEntity.ok(response);
    }
}