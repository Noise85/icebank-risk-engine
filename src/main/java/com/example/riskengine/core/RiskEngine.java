package com.example.riskengine.core;

import com.example.riskengine.api.RiskEngineVisitor;
import com.example.riskengine.event.OrderEventListener;

public interface RiskEngine extends RiskEngineVisitor, OrderEventListener {
}
