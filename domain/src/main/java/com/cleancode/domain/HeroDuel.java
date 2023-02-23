package com.cleancode.domain;

public final class HeroDuel {

    private final Long id;
    private final Hero winner;
    private final Hero loser;

    public HeroDuel(Long id, Hero winner, Hero loser) {
        this.id = id;
        this.winner = winner;
        this.loser = loser;
    }

    public Long getId() {
        return id;
    }

    public Hero getWinner() {
        return winner;
    }

    public Hero getLoser() {
        return loser;
    }
}
