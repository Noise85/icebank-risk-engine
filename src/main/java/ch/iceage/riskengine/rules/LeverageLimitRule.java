package ch.iceage.riskengine.rules;

import ch.iceage.riskengine.api.Order;
import ch.iceage.riskengine.api.RiskDecision;
import ch.iceage.riskengine.api.RiskRule;
import ch.iceage.riskengine.model.FuturesOrder;

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
