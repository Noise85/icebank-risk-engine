package ch.iceage.riskengine.rules;

import ch.iceage.riskengine.api.Order;
import ch.iceage.riskengine.api.RiskDecision;
import ch.iceage.riskengine.api.RiskRule;
import ch.iceage.riskengine.strategy.GreeksProvider;

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
