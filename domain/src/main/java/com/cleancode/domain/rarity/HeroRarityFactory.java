package com.cleancode.domain.rarity;

public final class HeroRarityFactory {

    public final HeroRarity create(String rarityName) {
        if (rarityName == null) { return null; }
        if (rarityName.equalsIgnoreCase("Commun")) {
            return new HeroCommonRarity();
        } else if (rarityName.equalsIgnoreCase("Rare")) {
            return new HeroRareRarity();
        } else if (rarityName.equalsIgnoreCase("Légendaire")) {
            return new HeroLegendaryRarity();
        }
        return null;
    }

}
