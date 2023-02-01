package com.cleancode.domain.cards;

public final class AssassinCard extends Card {

    private static final int BONUS_AGAINST_TANK = 30;

    public AssassinCard() {
        super();
    }

    public AssassinCard(String name, CardRarities rarity) {
        super(name, 800, 0, 200, 5, CardSpecialities.ASSASSIN, rarity, 1);
    }

}