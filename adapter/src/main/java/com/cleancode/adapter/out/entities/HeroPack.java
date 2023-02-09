package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "HERO_PACK")
public class HeroPack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int requiredTokens;
    private int numberOfCards;
    private double commonProbability;
    private double rareProbability;
    private double legendaryProbability;

    public HeroPack() {}
}
