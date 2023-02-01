package com.cleancode.domain;

public final class Duel {
    
    private final Player enemy;
    private final boolean victory;

    public Duel(Player enemy, boolean victory) {
        this.enemy = enemy;
        this.victory = victory;
    }
}
