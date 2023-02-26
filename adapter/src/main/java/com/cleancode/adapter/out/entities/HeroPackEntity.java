package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "HERO_PACK")
public final class HeroPackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int requiredTokens;

    private int numberOfCards;

    private float commonChance;

    private float rareChance;

    private float legendaryChance;

    public HeroPackEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRequiredTokens() {
        return requiredTokens;
    }

    public void setRequiredTokens(int requiredTokens) {
        this.requiredTokens = requiredTokens;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    public float getCommonChance() {
        return commonChance;
    }

    public void setCommonChance(float commonChance) {
        this.commonChance = commonChance;
    }

    public float getRareChance() {
        return rareChance;
    }

    public void setRareChance(float rareChance) {
        this.rareChance = rareChance;
    }

    public float getLegendaryChance() {
        return legendaryChance;
    }

    public void setLegendaryChance(float legendaryChance) {
        this.legendaryChance = legendaryChance;
    }

}
