package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset-disposals")
public class AssetDisposalController {

    @Autowired
    private AssetDisposalService service;

    @PostMapping
    public ResponseEntity<AssetDisposal> requestDisposal(@RequestBody AssetDisposal disposal) {
        return ResponseEntity.ok(service.requestDisposal(disposal));
    }

    @PutMapping("/approve/{disposalId}/{adminId}")
    public ResponseEntity<AssetDisposal> approveDisposal(
            @PathVariable Long disposalId,
            @PathVariable Long adminId) {
        return ResponseEntity.ok(service.approveDisposal(disposalId, adminId));
    }

    @GetMapping
    public ResponseEntity<List<AssetDisposal>> getAllDisposals() {
        return ResponseEntity.ok(service.getAllDisposals());
    }
}
