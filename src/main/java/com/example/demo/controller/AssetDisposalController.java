package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset-disposals")
@CrossOrigin
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(AssetDisposalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AssetDisposal> requestDisposal(
            @RequestBody AssetDisposal disposal) {

        AssetDisposal saved = service.requestDisposal(disposal);
        return new ResponseEntity<>(saved, HttpStatus.CREATED); // ✅ 201
    }

    @GetMapping
    public List<AssetDisposal> getAllDisposals() {
        return service.getAllDisposals();
    }
}
