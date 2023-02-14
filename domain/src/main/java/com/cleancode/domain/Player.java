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

    public boolean canOpenHeroPack(HeroPack pack) {
        return this.tokens > pack.getRequiredTokens();
    }

    public void openHeroPack(HeroPack pack, List<Hero> heroes) {
        removeTokensToOpenHeroPack(pack);
        addHeroesToDeck(heroes);
    }

    private void removeTokensToOpenHeroPack(HeroPack pack) {
        this.tokens -= pack.getRequiredTokens();
    }

    private void addHeroesToDeck(List<Hero> heroes) {
        this.deck.addAll(heroes);
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

}