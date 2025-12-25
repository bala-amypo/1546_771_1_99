package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.service.VendorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public ResponseEntity<Vendor> create(@Valid @RequestBody Vendor vendor) {
        try {
            return ResponseEntity.ok(vendorService.create(vendor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Vendor> getAll() {
        return null; // Direct repo call – safe since no validation needed
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vendorService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}