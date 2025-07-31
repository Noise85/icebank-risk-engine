package ch.iceage.riskengine.model;

import ch.iceage.riskengine.api.Order;
import ch.iceage.riskengine.api.RiskEngineVisitor;

//TODO this is only a stub order. The complete DTO's live inside the book engine project.
public record SpotOrder(String userId, double size, double price) implements Order {

    public void accept(RiskEngineVisitor visitor) {
        visitor.check(this);
    }
}