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
        return this.tokens > pack.getRequiredTokens();
    }

    public void openHeroPack(HeroPack pack, List<Hero> heroes) {
        removeTokensToOpenHeroPack(pack);
        addHeroesToDeck(heroes);
    }

    private void removeTokensToOpenHeroPack(HeroPack pack) {
        this.tokens -= pack.getRequiredTokens();
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