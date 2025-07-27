package com.example.riskengine.rules;

import com.example.riskengine.api.Order;
import com.example.riskengine.api.RiskDecision;
import com.example.riskengine.api.RiskRule;
import com.example.riskengine.strategy.MarginCalculator;

public class InitialMarginRule implements RiskRule {

    private final MarginCalculator calculator;

    public InitialMarginRule(MarginCalculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public RiskDecision check(Order order) {
        double required = calculator.requiredMargin(order);
        double available = calculator.availableMargin(order.userId());
        if (available >= required) {
            return RiskDecision.allow();
        }
        return RiskDecision.deny("Insufficient margin: required=" + required + ", available=" + available);
    }
}
