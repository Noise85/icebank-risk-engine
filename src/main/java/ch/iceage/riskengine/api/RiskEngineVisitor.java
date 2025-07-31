package ch.iceage.riskengine.api;

import ch.iceage.riskengine.model.FuturesOrder;
import ch.iceage.riskengine.model.OptionsOrder;
import ch.iceage.riskengine.model.SpotOrder;

public interface RiskEngineVisitor {
    RiskDecision check(SpotOrder order);

    RiskDecision check(FuturesOrder order);

    RiskDecision check(OptionsOrder order);
}