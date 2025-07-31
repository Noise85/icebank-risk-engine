package ch.iceage.riskengine.rules;

import ch.iceage.riskengine.api.Order;
import ch.iceage.riskengine.api.RiskDecision;
import ch.iceage.riskengine.api.RiskRule;
import ch.iceage.riskengine.model.SpotOrder;

import java.util.function.BiFunction;

public class SpotBalanceRule implements RiskRule {

    private final BiFunction<String, SpotOrder, Boolean> hasSufficientBalance;

    public SpotBalanceRule(BiFunction<String, SpotOrder, Boolean> hasSufficientBalance) {
        this.hasSufficientBalance = hasSufficientBalance;
    }

    @Override
    public RiskDecision check(Order order) {
        if (order instanceof SpotOrder spotOrder) {
            boolean ok = hasSufficientBalance.apply(spotOrder.userId(), spotOrder);
            return ok ? RiskDecision.allow() : RiskDecision.deny("Insufficient balance for spot order");
        }
        return RiskDecision.allow();
    }
}
