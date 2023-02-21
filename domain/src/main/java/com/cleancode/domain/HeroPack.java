package com.cleancode.domain;

public final class HeroPack {

    private final Long id;
    private final String name;
    private final int requiredTokens;
    private final int numberOfCards;
    private final float commonChance;
    private final float rareChance;
    private final float legendaryChance;

    public HeroPack(
        Long id,
        String name,
        int requiredTokens,
        int numberOfCards,
        float commonProbability,
        float rareProbability,
        float legendaryProbability
    ) {
        this.id = id;
        this.name = name;
        this.requiredTokens = requiredTokens;
        this.numberOfCards = numberOfCards;
        this.commonChance = commonProbability;
        this.rareChance = rareProbability;
        this.legendaryChance = legendaryProbability;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRequiredTokens() {
        return requiredTokens;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public float getCommonChance() {
        return commonChance;
    }

    public float getRareChance() {
        return rareChance;
    }

    public float getLegendaryChance() {
        return legendaryChance;
    }

}