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
@RequestMapping("/api/disposals")
@CrossOrigin(origins = "*")
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(AssetDisposalService service) {
        this.service = service;
    }

    /**
     * POST /api/disposals/request/{assetId}
     * Request disposal for an asset
     */
    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposalResponse> requestDisposal(
            @PathVariable Long assetId,
            @Valid @RequestBody AssetDisposalRequest requestDto) {

        // Map DTO to Entity
        AssetDisposal disposal = new AssetDisposal();
        disposal.setDisposalMethod(requestDto.getDisposalMethod());
        disposal.setDisposalValue(requestDto.getDisposalValue());

        // Save via service
        AssetDisposal saved = service.requestDisposal(assetId, disposal);

        // Map saved entity to response DTO
        AssetDisposalResponse response = new AssetDisposalResponse(saved);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * PUT /api/disposals/approve/{disposalId}/{adminId}
     * Approve a disposal request
     */
    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposalResponse> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        AssetDisposal approved = service.approveDisposal(disposalId, adminId);
        AssetDisposalResponse response = new AssetDisposalResponse(approved);

        return ResponseEntity.ok(response);
    }
}