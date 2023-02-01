package com.cleancode.adapter.out;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PLAYER")
public class PlayerEntity {

    @Id
    private String id;

    private String name;

    private int numberOfTokens;

    @OneToMany()
    private List<HeroEntity> deck;

    public PlayerEntity() {}
    
}
