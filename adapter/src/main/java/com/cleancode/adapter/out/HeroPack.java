package com.cleancode.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "HERO_PACK")
public class HeroPack {

    @Id
    private String id;
    private String name;
    private int requiredTokens;
    private int numberOfCards;
    private double commonProbability;
    private double rareProbability;
    private double legendaryProbability;

    public HeroPack() {}
}
