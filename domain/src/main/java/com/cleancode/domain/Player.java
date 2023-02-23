package com.cleancode.domain;

import lombok.Builder;
import lombok.Builder.Default;

import java.util.List;

@Builder
public final class Player {

    private final Long id;
    private final String name;
    @Default
    private final List<Hero> deck = List.of();
    @Default
    private int tokens = 4;

    public boolean canOpenHeroPack(HeroPack pack) {
        return this.tokens >= pack.getRequiredTokens();
    }

    public void openHeroPack(int tokensRequired, List<Hero> heroes) {
        removeTokensToOpenHeroPack(tokensRequired);
        addHeroesToDeck(heroes);
    }

    private void removeTokensToOpenHeroPack(int tokensToRemove) {
        this.tokens -= tokensToRemove;
    }

    public void addOneToken() { this.tokens++; }

    private void addHeroesToDeck(List<Hero> heroes) {
        this.deck.addAll(heroes);
    }

    public Long getId() {
        return id;
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