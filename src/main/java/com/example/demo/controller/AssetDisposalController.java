package com.example.demo.controller;

import com.example.demo.entity.AssetDisposal;
import com.example.demo.service.AssetDisposalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asset-disposals")
public class AssetDisposalController {

    private final AssetDisposalService service;

    public AssetDisposalController(AssetDisposalService service) {
        this.service = service;
    }

    @PostMapping
    public AssetDisposal requestDisposal(
            @RequestBody AssetDisposal disposal) {
        return service.requestDisposal(disposal);
    }
}
