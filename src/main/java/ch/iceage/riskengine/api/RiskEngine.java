package ch.iceage.riskengine.api;

import ch.iceage.riskengine.event.OrderEventListener;

public interface RiskEngine extends RiskEngineVisitor, OrderEventListener {

}