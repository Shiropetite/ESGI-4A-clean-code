package com.cleancode.domain.cards;

public final class MageCard extends Card {

    private static final int BONUS_AGAINST_ASSASSIN = 25;

    public MageCard() { super(); }

    public MageCard(String name, CardRarities rarity) {
        super(name, 700, 0, 150, 10, CardSpecialities.MAGE, rarity, 1);
    }

}