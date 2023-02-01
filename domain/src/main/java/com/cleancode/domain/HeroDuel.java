package com.cleancode.domain;

public final class HeroDuel {
    
    private final Hero opponent;
    private final boolean victory;

    public HeroDuel(Hero opponent, boolean victory) {
        this.opponent = opponent;
        this.victory = victory;
    }
}
