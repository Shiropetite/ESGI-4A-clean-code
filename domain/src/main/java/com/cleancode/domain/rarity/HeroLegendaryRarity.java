package com.cleancode.domain.rarity;

public class HeroLegendaryRarity implements HeroRarity {
    @Override
    public int applyRarityFactor(int heroStat) {
        return (int) Math.round(heroStat * 0.2);
    }
}
