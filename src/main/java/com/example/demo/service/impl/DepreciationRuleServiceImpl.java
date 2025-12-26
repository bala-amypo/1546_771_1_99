package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.DepreciationRule;
import com.example.demo.repository.DepreciationRuleRepository;
import com.example.demo.service.DepreciationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DepreciationServiceImpl implements DepreciationService {

    private final DepreciationRuleRepository repository;

    public DepreciationServiceImpl(DepreciationRuleRepository repository) {
        this.repository = repository;
    }

    // ---------- CRUD ----------

    @Override
    public DepreciationRule save(DepreciationRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<DepreciationRule> getAll() {
        return repository.findAll();
    }

    @Override
    public DepreciationRule getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public DepreciationRule getByRuleName(String ruleName) {
        return repository.findByRuleName(ruleName).orElse(null);
    }

    // ---------- CALCULATION (USED IN TEST CASES) ----------

    public Double calculateDepreciation(Asset asset) {

        if (asset == null ||
            asset.getPurchaseCost() == null ||
            asset.getPurchaseDate() == null ||
            asset.getDepreciationRule() == null) {
            return 0.0;
        }

        DepreciationRule rule = asset.getDepreciationRule();

        if (!"SLM".equalsIgnoreCase(rule.getMethod())) {
            return 0.0;
        }

        long yearsUsed = ChronoUnit.YEARS.between(
                asset.getPurchaseDate(),
                LocalDate.now()
        );

        if (yearsUsed <= 0 || rule.getUsefulLifeYears() <= 0) {
            return 0.0;
        }

        double depreciableAmount =
                asset.getPurchaseCost() - rule.getSalvageValue();

        double annualDepreciation =
                depreciableAmount / rule.getUsefulLifeYears();

        double totalDepreciation =
                annualDepreciation * yearsUsed;

        return Math.min(totalDepreciation, depreciableAmount);
    }
}
