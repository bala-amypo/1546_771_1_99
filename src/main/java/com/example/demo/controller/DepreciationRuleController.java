package com.example.demo.controller;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.service.DepreciationRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
public class DepreciationRuleController {
    @Autowired private DepreciationRuleService ruleService;

    @PostMapping
    public DepreciationRule createRule(@RequestBody DepreciationRule rule) { return ruleService.createRule(rule); }
}