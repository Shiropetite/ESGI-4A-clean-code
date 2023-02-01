package com.cleancode.domain.rarity;

public class HeroRareRarity implements HeroRarity {
    @Override
    public int applyRarityFactor(int heroStat) {
        return (int) Math.round(heroStat * 0.1);
    }
}
