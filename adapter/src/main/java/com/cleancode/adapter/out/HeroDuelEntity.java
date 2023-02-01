package com.cleancode.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HERO_DUEL")
public class HeroDuelEntity {

    @Id
    private String id;

    @ManyToOne()
    private HeroEntity enemy;

   private boolean victory;

    public HeroDuelEntity() {}

}
