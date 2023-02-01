package com.cleancode.domain.cards;

public enum CardRarities {
    COMMON(0),
    RARE(0.1),
    LEGENDARY(0.2);

    private final double percent;

    CardRarities(double percent) {
        this.percent = percent;
    }

    public double getPercent() { return this.percent; }
}
