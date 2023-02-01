package com.cleancode.domain.cards;

public final class TankCard extends Card {
    private static final int BONUS_AGAINST_MAGE = 20;

    public TankCard() {
        super();
    }

    public TankCard(String name, CardRarities rarity) {
        super(name, 1000, 0, 100, 20, CardSpecialities.TANK, rarity, 1);
    }

    public TankCard(String name, CardRarities rarity, long level) {
        super(name, 1000, 0, 100, 20, CardSpecialities.TANK, rarity, (int) level);
    }

}