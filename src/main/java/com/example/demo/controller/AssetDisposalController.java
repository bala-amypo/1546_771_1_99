package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asset-disposals")
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(AssetDisposalService service) {
        this.service = service;
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<AssetDisposal> requestDisposal(@PathVariable Long assetId,
                                                         @RequestBody AssetDisposal disposal) {
        return ResponseEntity.ok(service.requestDisposal(assetId, disposal));
    }

    @GetMapping
    public ResponseEntity<List<AssetDisposal>> getAllDisposals() {
        return ResponseEntity.ok(service.getAllDisposals());
    }
}
