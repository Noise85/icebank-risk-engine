package ch.iceage.riskengine.api;

//TODO this is only a stub order. The complete DTO's live inside the book engine project.
public interface Order {
    String userId();

    double size();

    double price();

    void accept(RiskEngineVisitor visitor);
}