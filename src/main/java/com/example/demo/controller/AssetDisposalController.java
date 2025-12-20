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

    // ✅ POST → 201 CREATED
    @PostMapping
    public ResponseEntity<?> requestDisposal(@RequestBody AssetDisposal disposal) {
        try {
            AssetDisposal saved = service.requestDisposal(disposal);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            e.printStackTrace(); // 🔴 check console output
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // GET → 200 OK
    @GetMapping
    public List<AssetDisposal> getAllDisposals() {
        return service.getAllDisposals();
    }
}
