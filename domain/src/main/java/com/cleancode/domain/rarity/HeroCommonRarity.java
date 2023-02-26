package com.cleancode.domain.rarity;

public final class HeroCommonRarity implements HeroRarity {

    @Override
    public final float applyFactor(float heroStat) {
        return heroStat;
    }

    @Override
    public final String getName() {
        return "Commun";
    }

}
