package com.cleancode.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public final class Hero {

    private static final int MAX_LEVEL = 100;
    private final Long id;
    private final HeroRef ref;
    private int xp;
    private int level;

    public Hero(HeroRef ref) {
        this.id = 0L;
        this.ref = ref;
        this.xp = 0;
        this.level = 1;
    }

    public Hero(Long id, HeroRef ref, int experiencePoints, int level) {
        this.id = id;
        this.ref = ref;
        this.xp = experiencePoints;
        this.level = level;
    }

    public boolean canDuel(Hero opponent) {
        return opponent.level >= this.level;
    }

    public void gainXp() {
        this.xp++;
        if (this.xp % 5 == 0) {
            this.levelUp();
        }
    }

    public void levelUp() {
        if (this.level == MAX_LEVEL) { throw new RuntimeException("Le niveau maximal est déjà atteint"); }
        this.level++;
    }

    @JsonIgnore
    public float getDamage(Hero opponent, int bonus) {
        return this.getPowerPoints() - opponent.getArmorPoints() + bonus;
    }

    @JsonIgnore
    public float getMaxHealthPoints() {
        return this.ref.getMaxHealthPoints() + (this.ref.getMaxHealthPoints() * ((level - 1) / 10f));
    }

    @JsonIgnore
    public float getPowerPoints() {
        return this.ref.getPowerPoints() + (this.ref.getPowerPoints() * ((level - 1) / 10f));
    }

    @JsonIgnore
    public float getArmorPoints() {
        return this.ref.getArmorPoints() + (this.ref.getArmorPoints() * ((level - 1) / 10f));
    }

    public Long getId() {
        return id;
    }

    public HeroRef getRef() {
        return ref;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return id.equals(hero.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
