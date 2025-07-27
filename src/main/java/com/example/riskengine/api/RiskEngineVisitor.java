package com.example.riskengine.api;

import com.example.riskengine.model.FuturesOrder;
import com.example.riskengine.model.OptionsOrder;
import com.example.riskengine.model.SpotOrder;

public interface RiskEngineVisitor {
    RiskDecision check(SpotOrder order);

    RiskDecision check(FuturesOrder order);

    RiskDecision check(OptionsOrder order);
}