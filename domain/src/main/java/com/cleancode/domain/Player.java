package com.cleancode.domain;

import com.cleancode.domain.cards.Card;
import com.cleancode.domain.packs.CardPack;

import java.util.ArrayList;
import java.util.List;

public final class Player {

    private final String name;
    private final int numberOfTokens;
    private final List<Card> deck;
    private final List<Duel> duels;

    private Player(String name, int numberOfTokens, List<Card> deck, List<Duel> duels) {
        this.name = name;
        this.numberOfTokens = numberOfTokens;
        this.deck = deck;
        this.duels = duels;
    }

    private Player(String name) {
        this(name, 4, new ArrayList<>(), new ArrayList<>());
    }

    public void openCardPack(CardPack cardPack) {
        this.deck.addAll(cardPack.open());
    }
}