package com.cleancode.domain;

public final class HeroBonus {

    private final Long id;
    private final String strong;
    private final String weak;
    private final int bonus;

    public HeroBonus(Long id, String strong, String weak, int bonus) {
        this.id = id;
        this.strong = strong;
        this.weak = weak;
        this.bonus = bonus;
    }

    public Long getId() {
        return id;
    }

    public String getStrong() {
        return strong;
    }

    public String getWeak() {
        return weak;
    }

    public int getBonus() {
        return bonus;
    }

}
