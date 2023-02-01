package com.cleancode.domain.rarity;

public class HeroCommonRarity implements HeroRarity {
    @Override
    public int applyRarityFactor(int heroStat) {
        return heroStat;
    }
}
