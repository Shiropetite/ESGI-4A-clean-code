package com.cleancode.adapter.out;

import jakarta.persistence.*;

@Entity
@Table(name = "REF_HERO")
public class RefHeroEntity {

    @Id
    private String id;

    private String name;

    private int maxHealthPoints;

    private int powerPoints;

    private int armorPoints;

    @ManyToOne
    private HeroPack rarity;

    public RefHeroEntity() {}

}
