package com.cleancode.domain.rarity;

public class HeroLegendaryRarity implements HeroRarity {

    @Override
    public float applyFactor(float heroStat) { return heroStat + Math.round(heroStat * 0.2); }

    @Override
    public String getName() {
        return "Légendaire";
    }

}
