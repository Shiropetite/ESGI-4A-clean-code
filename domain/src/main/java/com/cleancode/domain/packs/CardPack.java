package com.cleancode.domain.packs;

import com.cleancode.domain.cards.Card;
import com.cleancode.domain.cards.CardFactory;
import com.cleancode.domain.cards.CardRarities;
import com.cleancode.domain.cards.CardSpecialities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class CardPack {

    private final int numberOfTokens;
    private final int numberOfCards;
    private final double commonChance;
    private final double rareChance;
    private final double legendaryChance;

    public CardPack(
            int numberOfTokens,
            int numberOfCards,
            double commonChance,
            double rareChance,
            double legendaryChance
    ) {
        this.numberOfTokens = numberOfTokens;
        this.numberOfCards = numberOfCards;
        this.commonChance = commonChance;
        this.rareChance = rareChance;
        this.legendaryChance = legendaryChance;
    }
    
    public List<Card> open() {
        List<Card> cards = new ArrayList<>();
        // TODO: fix
       /* for (int i = 0; i < this.numberOfCards; i++) {
            cards.add(generateCard());
        }*/

        return cards;
    }

    private CardRarities rollCardRarity(double roll) {
        CardRarities cardRarity = null;
        if (roll <= this.legendaryChance) { cardRarity = CardRarities.LEGENDARY; }
        else if (roll <= this.rareChance + this.legendaryChance) { cardRarity = CardRarities.RARE; }
        else if (roll <= this.commonChance + this.rareChance + this.legendaryChance) { cardRarity = CardRarities.COMMON; }

        return cardRarity;
    }

    private CardSpecialities rollCardSpeciality(int roll) {
        return CardSpecialities.values()[roll];
    }
}