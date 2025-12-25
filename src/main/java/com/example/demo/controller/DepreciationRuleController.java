package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depreciation-rules")
public class DepreciationRuleController {

    private final DepreciationRuleService service;

    public DepreciationRuleController(DepreciationRuleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DepreciationRule>> getAllRules() {
        return ResponseEntity.ok(service.getAllRules());
    }
}
