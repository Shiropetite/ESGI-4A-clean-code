package com.cleancode.domain;

import lombok.Builder;
import lombok.Builder.Default;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
public final class Player {

    private final Long id;
    private final String name;
    @Default
    private final List<Hero> deck = new ArrayList<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}