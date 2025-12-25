package com.example.demo.service;

import com.example.demo.entity.DepreciationRule;

public interface DepreciationRuleService {
    DepreciationRule create(DepreciationRule rule);
    DepreciationRule findById(Long id);
}