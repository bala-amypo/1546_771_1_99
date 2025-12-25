package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {

    private final DepreciationRuleRepository repository;

    public DepreciationRuleController(DepreciationRuleRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DepreciationRule rule) {
        if (!Set.of("STRAIGHT_LINE", "DECLINING_BALANCE").contains(rule.getMethod())) {
            return ResponseEntity.badRequest().body("Invalid method");
        }
        return ResponseEntity.ok(repository.save(rule));
    }
}