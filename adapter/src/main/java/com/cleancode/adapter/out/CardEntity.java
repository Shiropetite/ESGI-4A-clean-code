package com.cleancode.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARD")
public class CardEntity {

    @Id
    private String id;

    private String name;

    private int maxHealthPoints;

    private int healthPoints;

    private int experiencePoints;

    private int powerPoints;

    private int armorPoints;

    private int speciality;

    private int rarity;

    private int level;

    public CardEntity() {}

}
