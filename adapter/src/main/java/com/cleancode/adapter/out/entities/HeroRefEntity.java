package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "HERO_REF")
public class HeroRefEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float maxHealthPoints;

    private float powerPoints;

    private float armorPoints;

    private String rarity;

    public HeroRefEntity() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxHealthPoints() { return maxHealthPoints; }

    public void setMaxHealthPoints(float maxHealthPoints) {
        this.maxHealthPoints = maxHealthPoints;
    }

    public float getPowerPoints() {
        return powerPoints;
    }

    public void setPowerPoints(float powerPoints) {
        this.powerPoints = powerPoints;
    }

    public float getArmorPoints() {
        return armorPoints;
    }

    public void setArmorPoints(float armorPoints) {
        this.armorPoints = armorPoints;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
