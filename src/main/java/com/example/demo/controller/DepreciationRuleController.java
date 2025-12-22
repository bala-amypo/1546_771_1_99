package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/depreciation-rules")
@CrossOrigin
public class DepreciationRuleController {

    private final DepreciationRuleService service;

    public DepreciationRuleController(DepreciationRuleService service) {
        this.service = service;
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepreciationRule createRule(@RequestBody DepreciationRule rule) {
        return service.saveRule(rule);
    }

    
    @GetMapping
    public List<DepreciationRule> getAllRules() {
        return service.getAllRules();
    }
}
