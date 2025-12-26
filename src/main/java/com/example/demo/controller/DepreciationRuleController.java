package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
@Tag(name = "Depreciation Rule Controller", description = "Endpoints for managing depreciation logic")
public class DepreciationRuleController {

    @Autowired
    private DepreciationRuleService ruleService;

    @PostMapping
    public DepreciationRule createRule(@RequestBody DepreciationRule rule) {
        return ruleService.createRule(rule);
    }
}