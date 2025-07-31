package ch.iceage.riskengine.rules;

import ch.iceage.riskengine.api.Order;
import ch.iceage.riskengine.api.RiskDecision;
import ch.iceage.riskengine.api.RiskRule;
import ch.iceage.riskengine.strategy.MarginCalculator;

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
