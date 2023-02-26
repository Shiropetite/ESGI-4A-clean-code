package com.cleancode.domain.rarity;

public final class HeroRareRarity implements HeroRarity {

    @Override
    public final float applyFactor(float heroStat) { return heroStat + Math.round(heroStat * 0.1); }

    @Override
    public final String getName() {
        return "Rare";
    }

}
