package com.example.demo.service;

import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepreciationRuleService {

    @Autowired
    private DepreciationRuleRepository ruleRepository;

    public DepreciationRule createRule(DepreciationRule rule) {
        if (rule.getUsefulLifeYears() == null || rule.getUsefulLifeYears() <= 0) {
            throw new RuntimeException("Useful life years must be positive");
        }
        if (rule.getSalvageValue() == null || rule.getSalvageValue() < 0) {
            throw new RuntimeException("Salvage value cannot be negative");
        }
        if (!isValidMethod(rule.getMethod())) {
            throw new RuntimeException("Invalid depreciation method");
        }
        return ruleRepository.save(rule);
    }

    private boolean isValidMethod(String method) {
        return "STRAIGHT_LINE".equals(method) || "DECLINING_BALANCE".equals(method);
    }

    public List getAllRules() {
        return ruleRepository.findAll();
    }
    }
