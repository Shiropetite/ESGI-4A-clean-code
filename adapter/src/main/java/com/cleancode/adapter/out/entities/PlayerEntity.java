package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PLAYER")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int numberOfTokens;

    @OneToMany()
    private List<HeroEntity> deck;

    public PlayerEntity() {}
    
}
