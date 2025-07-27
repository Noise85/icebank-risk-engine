package com.example.riskengine.rules;

import com.example.riskengine.api.Order;
import com.example.riskengine.api.RiskDecision;
import com.example.riskengine.api.RiskRule;
import com.example.riskengine.model.FuturesOrder;

public class LeverageLimitRule implements RiskRule {

    private final double maxLeverage;

    public LeverageLimitRule(double maxLeverage) {
        this.maxLeverage = maxLeverage;
    }

    @Override
    public RiskDecision check(Order order) {
        if (order instanceof FuturesOrder futuresOrder) {
            if (futuresOrder.leverage() > maxLeverage) {
                return RiskDecision.deny("Leverage too high: " + futuresOrder.leverage());
            }
        }
        return RiskDecision.allow();
    }
}
