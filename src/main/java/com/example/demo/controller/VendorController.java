package com.example.demo.controller;

import com.example.demo.entity.Vendor;
import com.example.demo.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public ResponseEntity<?> createVendor(@RequestBody Vendor vendor) {
        try {
            return ResponseEntity.ok(vendorService.createVendor(vendor));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVendorById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(vendorService.getVendorById(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}