package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "HERO_DUEL")
public class HeroDuelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private HeroEntity enemy;

   private boolean victory;

    public HeroDuelEntity() {}

}
