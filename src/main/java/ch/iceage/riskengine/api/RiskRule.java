package ch.iceage.riskengine.api;

public interface RiskRule {
    RiskDecision check(Order order);
}