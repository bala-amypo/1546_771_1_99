package com.example.demo.service;
import com.example.demo.service.DepreciationRule;

import java.util.List;


public interface DepreciationRule {
    DepreciationRule createRule(DepreciationRule rule);
    List<DepreciationRule> getAllRules();

    
}
