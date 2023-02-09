package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "HERO")
public class HeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int healthPoints;

    private int experiencePoints;

    private int level;

    @ManyToOne()
    private RefHeroEntity refHero;

    @OneToMany()
    private List<HeroDuelEntity> duels;

    public HeroEntity() {}

}
