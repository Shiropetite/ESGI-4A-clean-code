package com.cleancode.domain.rarity;

public final class HeroLegendaryRarity implements HeroRarity {

    @Override
    public final float applyFactor(float heroStat) { return heroStat + Math.round(heroStat * 0.2); }

    @Override
    public final String getName() {
        return "Légendaire";
    }

}
