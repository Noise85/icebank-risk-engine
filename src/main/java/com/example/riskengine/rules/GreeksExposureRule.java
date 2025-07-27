package com.example.riskengine.rules;

import com.example.riskengine.api.Order;
import com.example.riskengine.api.RiskDecision;
import com.example.riskengine.api.RiskRule;
import com.example.riskengine.strategy.GreeksProvider;

public class GreeksExposureRule implements RiskRule {

    private final GreeksProvider greeksProvider;
    private final double maxDelta;

    public GreeksExposureRule(GreeksProvider greeksProvider, double maxDelta) {
        this.greeksProvider = greeksProvider;
        this.maxDelta = maxDelta;
    }

    @Override
    public RiskDecision check(Order order) {
        double delta = Math.abs(greeksProvider.delta(order));
        if (delta > maxDelta) {
            return RiskDecision.deny("Delta exposure too high: " + delta);
        }
        return RiskDecision.allow();
    }
}
