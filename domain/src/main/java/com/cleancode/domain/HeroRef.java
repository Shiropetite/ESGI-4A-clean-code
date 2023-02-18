package com.cleancode.domain;

import com.cleancode.domain.rarity.HeroRarity;
import com.cleancode.domain.rarity.HeroRarityFactory;
import lombok.Builder;

public class HeroRef {

    private final String name;
    private final float maxHealthPoints;
    private final float powerPoints;
    private final float armorPoints;
    private final HeroRarity rarity;

    public HeroRef(String name, float maxHealthPoints, float powerPoints, float armorPoints, String rarity) {
        this.name = name;
        this.rarity = new HeroRarityFactory().create(rarity);
        this.maxHealthPoints = this.rarity.applyFactor(maxHealthPoints);
        this.powerPoints = this.rarity.applyFactor(powerPoints);
        this.armorPoints = this.rarity.applyFactor(armorPoints);
    }

    public String getName() { return name; }

    public float getMaxHealthPoints() { return maxHealthPoints; }

    public float getPowerPoints() { return powerPoints; }

    public float getArmorPoints() { return armorPoints; }

    public HeroRarity getRarity() {
        return rarity;
    }

}
