package com.cleancode.domain.rarity;

public class HeroRareRarity implements HeroRarity {

    @Override
    public float applyFactor(float heroStat) { return heroStat + Math.round(heroStat * 0.1); }

    @Override
    public String getName() {
        return "Rare";
    }

}
