package ch.iceage.riskengine.model;

import ch.iceage.riskengine.api.Order;
import ch.iceage.riskengine.api.RiskEngineVisitor;

//TODO this is only a stub order. The complete DTO's live inside the book engine project.
public record OptionsOrder(String userId, double size, double price, double strike, long expiry) implements Order {
    public void accept(RiskEngineVisitor visitor) {
        visitor.check(this);
    }
}