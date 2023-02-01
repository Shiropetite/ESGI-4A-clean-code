package com.cleancode.domain;

import com.cleancode.domain.rarity.HeroRarity;

public class RefHero {

    private final String name;
    private final int maxHealthPoints;
    private final int powerPoints;
    private final int armorPoints;
    private final HeroRarity rarity;

    public RefHero(String name, int maxHealthPoints, int powerPoints, int armorPoints, HeroRarity rarity) {
        this.name = name;
        this.rarity = rarity;
        this.maxHealthPoints = rarity.applyRarityFactor(maxHealthPoints);
        this.powerPoints = rarity.applyRarityFactor(powerPoints);
        this.armorPoints = rarity.applyRarityFactor(armorPoints);
    }

    public int getMaxHealthPoints() { return maxHealthPoints; }

    public int getPowerPoints() { return powerPoints; }

    public int getArmorPoints() { return armorPoints; }

}
