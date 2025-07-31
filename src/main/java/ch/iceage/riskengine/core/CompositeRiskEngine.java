package ch.iceage.riskengine.core;

import ch.iceage.riskengine.api.*;
import ch.iceage.riskengine.api.RiskEngine;
import ch.iceage.riskengine.model.FuturesOrder;
import ch.iceage.riskengine.model.OptionsOrder;
import ch.iceage.riskengine.model.SpotOrder;

import java.util.List;

public class CompositeRiskEngine implements RiskEngine {

    private final List<RiskRule> spotRules;
    private final List<RiskRule> futuresRules;
    private final List<RiskRule> optionsRules;

    public CompositeRiskEngine(List<RiskRule> spotRules,
                               List<RiskRule> futuresRules,
                               List<RiskRule> optionsRules) {
        this.spotRules = spotRules;
        this.futuresRules = futuresRules;
        this.optionsRules = optionsRules;
    }

    @Override
    public void on(OrderEvent event) {
        event.getOrder().accept(this);
    }

    @Override
    public RiskDecision check(SpotOrder order) {
        return this.checkInternal(spotRules, order);
    }

    @Override
    public RiskDecision check(FuturesOrder order) {
        return this.checkInternal(futuresRules, order);
    }

    @Override
    public RiskDecision check(OptionsOrder order) {
        return this.checkInternal(optionsRules, order);
    }

    private RiskDecision checkInternal(List<RiskRule> rules, Order order) {
        for (RiskRule rule : rules) {
            RiskDecision result = rule.check(order);
            if (!result.allowed()) return result;
        }
        return RiskDecision.allow();
    }
}