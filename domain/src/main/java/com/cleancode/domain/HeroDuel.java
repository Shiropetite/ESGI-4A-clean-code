package com.cleancode.domain;

public final class HeroDuel {

    private final Long id;
    private final Hero opponent;
    private final boolean victory;

    public HeroDuel(Long id, Hero opponent, boolean victory) {
        this.id = id;
        this.opponent = opponent;
        this.victory = victory;
    }

    public Long getId() {
        return id;
    }

    public Hero getOpponent() {
        return opponent;
    }

    public boolean isVictory() {
        return victory;
    }

}
