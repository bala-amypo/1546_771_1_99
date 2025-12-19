package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public AssetDisposal requestDisposal(@RequestBody AssetDisposal disposal) {
        return service.requestDisposal(disposal);
    }

    // GET
    @GetMapping
    public List<AssetDisposal> getAllDisposals() {
        return service.getAllDisposals();
    }
}
