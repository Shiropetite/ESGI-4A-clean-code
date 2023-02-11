package com.cleancode.domain;


import java.util.ArrayList;
import java.util.List;

public class Hero {

    private static final int MAX_LEVEL = 100;
    private final HeroRef ref;
    private final List<HeroDuel> duels;
    private float healthPoints;
    private int experiencePoints;
    private int level;

    public Hero(HeroRef ref) {
        this.ref = ref;
        this.duels = new ArrayList<>();
        this.healthPoints = this.ref.getMaxHealthPoints();
        this.experiencePoints = 0;
        this.level = 1;
    }

    public Hero(HeroRef ref, List<HeroDuel> duels, float healthPoints, int experiencePoints, int level) {
        this.ref = ref;
        this.duels = duels;
        this.healthPoints = healthPoints;
        this.experiencePoints = experiencePoints;
        this.level = level;
    }

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

    public float getMaxHealthPoints() {
        return this.ref.getMaxHealthPoints() + this.ref.getMaxHealthPoints() * ((level - 1) / 10);
    }

    public float getPowerPoints() {
        return this.ref.getPowerPoints() + this.ref.getPowerPoints() * ((level - 1) / 10);
    }

    public float getArmorPoints() {
        return this.ref.getArmorPoints() + this.ref.getArmorPoints() * ((level - 1) / 10);
    }

    public HeroRef getRef() {
        return ref;
    }

    public List<HeroDuel> getDuels() {
        return duels;
    }

    public float getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(float healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
