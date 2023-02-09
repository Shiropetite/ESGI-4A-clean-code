package com.cleancode.domain.rarity;

public class HeroCommonRarity implements HeroRarity {

    @Override
    public float applyFactor(float heroStat) {
        return heroStat;
    }

    @Override
    public String getName() {
        return "Commun";
    }

}
