package com.example.riskengine.api;

import com.example.riskengine.event.OrderEventListener;

public interface RiskEngine extends RiskEngineVisitor, OrderEventListener {

}