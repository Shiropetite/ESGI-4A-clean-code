package com.cleancode.domain.cards;

public class CardFactory {

    private static CardFactory instance;

    private CardFactory() {};

    public static CardFactory getInstance() {
        if (instance == null) {
            instance = new CardFactory();
        }
        return instance;
    }

    public Card createCardFromSpeciality(CardSpecialities cardSpeciality) {
        return switch (cardSpeciality) {
            case TANK -> new TankCard();
            case ASSASSIN -> new AssassinCard();
            case MAGE -> new MageCard();
        };
    }
}
