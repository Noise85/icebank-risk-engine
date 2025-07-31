package ch.iceage.riskengine.api;

public record RiskDecision(boolean allowed, String reason) {
    public static RiskDecision allow() {
        return new RiskDecision(true, "");
    }

    public static RiskDecision deny(String reason) {
        return new RiskDecision(false, reason);
    }
}