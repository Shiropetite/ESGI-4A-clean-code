package com.cleancode.domain;

import java.util.ArrayList;
import java.util.List;

public final class Player {

    private final String name;
    private final List<Hero> deck;
    private int tokens;

    public Player(String name) {
        this.name = name;
        this.deck = new ArrayList<>();
        this.tokens = 4;
    }

    public Player(String name, List<Hero> deck, int tokens) {
        this.name = name;
        this.deck = deck;
        this.tokens = tokens;
    }

    public void openCardPack(HeroPack cardPack) {
        if (this.tokens < cardPack.getRequiredTokens()) {
            throw new RuntimeException("Vous n'avez pas assez de token pour ouvrir ce pack");
        }
        this.tokens -= cardPack.getRequiredTokens();
        this.deck.addAll(cardPack.open());
    }

    public String getName() {
        return name;
    }

    public List<Hero> getDeck() {
        return deck;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }
}