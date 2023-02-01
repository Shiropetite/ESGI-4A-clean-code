package com.cleancode.domain;

import java.util.ArrayList;
import java.util.List;

public class HeroPack {

    private final int requiredTokens;
    private final int numberOfCards;
    private final double commonProbability;
    private final double rareProbability;
    private final double legendaryProbability;

    public HeroPack(
        int requiredTokens,
        int numberOfCards,
        double commonProbability,
        double rareProbability,
        double legendaryProbability
    ) {
        this.requiredTokens = requiredTokens;
        this.numberOfCards = numberOfCards;
        this.commonProbability = commonProbability;
        this.rareProbability = rareProbability;
        this.legendaryProbability = legendaryProbability;
    }
    
    public List<Hero> open() {
        List<Hero> cards = new ArrayList<>();
        return cards;
    }

    public int getRequiredTokens() { return requiredTokens; }

}