package com.cleancode.domain;


import java.util.ArrayList;
import java.util.List;

public abstract class Hero {

    private static final int MAX_LEVEL = 100;
    private final RefHero ref;
    private final List<HeroDuel> duels;
    private int healthPoints;
    private int experiencePoints;
    private int level;

    public Hero(RefHero ref) {
        this.ref = ref;
        this.healthPoints = this.ref.getMaxHealthPoints();
        this.experiencePoints = 0;
        this.level = 1;
        this.duels = new ArrayList<>();
    };

    public void gainExp() {
        this.experiencePoints++;
        if (this.experiencePoints % 5 == 0) {
            this.levelUp();
        }
    }

    private void levelUp() {
        if (this.level == MAX_LEVEL) { throw new RuntimeException("Le niveau maximal est déjà atteint"); }
        this.level++;
    }

    public void heal() {
        this.healthPoints = this.getMaxHealthPoints();
    }

    public void attack(Hero opponent) {
        opponent.healthPoints += this.getArmorPoints() - this.getPowerPoints();
    }

    public int getMaxHealthPoints() {
        return this.ref.getMaxHealthPoints() + this.ref.getMaxHealthPoints() * ((level - 1) / 10);
    }

    public int getPowerPoints() {
        return this.ref.getPowerPoints() + this.ref.getPowerPoints() * ((level - 1) / 10);
    }

    public int getArmorPoints() {
        return this.ref.getArmorPoints() + this.ref.getArmorPoints() * ((level - 1) / 10);
    }

    public int getHealthPoints() { return this.healthPoints; }

    public int getExperiencePoints() {
        return experiencePoints;
    }
}
