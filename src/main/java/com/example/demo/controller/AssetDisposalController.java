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

    @PostMapping("/request/{assetId}")
    public ResponseEntity<AssetDisposalResponse> requestDisposal(
            @PathVariable Long assetId,
            @Valid @RequestBody AssetDisposalRequest requestDto) {

        AssetDisposal disposal = new AssetDisposal();
        disposal.setDisposalMethod(requestDto.getDisposalMethod());
        disposal.setDisposalValue(requestDto.getDisposalValue());

        AssetDisposal saved = service.requestDisposal(assetId, disposal);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AssetDisposalResponse(saved));
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposalResponse> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {

        AssetDisposal approved = service.approveDisposal(disposalId, adminId);
        return ResponseEntity.ok(new AssetDisposalResponse(approved));
    }
}