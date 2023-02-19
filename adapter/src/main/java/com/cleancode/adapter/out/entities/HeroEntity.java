package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "HERO")
public class HeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private HeroRefEntity ref;

    @OneToMany()
    private List<HeroDuelEntity> duels;

    private float healthPoints;

    private int experiencePoints;

    private int level;

    public HeroEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HeroRefEntity getRef() {
        return ref;
    }

    public void setRef(HeroRefEntity refHero) {
        this.ref = refHero;
    }

    public List<HeroDuelEntity> getDuels() {
        return duels;
    }

    public void setDuels(List<HeroDuelEntity> duels) {
        this.duels = duels;
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
