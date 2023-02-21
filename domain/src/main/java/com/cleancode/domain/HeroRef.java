package com.cleancode.domain;

import com.cleancode.domain.rarity.HeroRarity;
import com.cleancode.domain.rarity.HeroRarityFactory;

public final class HeroRef {

    private final Long id;
    private final String name;
    private float maxHealthPoints;
    private float powerPoints;
    private float armorPoints;
    private final HeroRarity rarity;

    public HeroRef(Long id, String name, float maxHealthPoints, float powerPoints, float armorPoints, String rarity) {
        this.id = id;
        this.name = name;
        this.rarity = new HeroRarityFactory().create(rarity);
        this.maxHealthPoints = maxHealthPoints;
        this.powerPoints = powerPoints;
        this.armorPoints = armorPoints;
    }

    public void applyRarityFactor() {
        this.maxHealthPoints = this.rarity.applyFactor(maxHealthPoints);
        this.powerPoints = this.rarity.applyFactor(powerPoints);
        this.armorPoints = this.rarity.applyFactor(armorPoints);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public float getPowerPoints() {
        return powerPoints;
    }

    public float getArmorPoints() {
        return armorPoints;
    }

    public HeroRarity getRarity() {
        return rarity;
    }

}
