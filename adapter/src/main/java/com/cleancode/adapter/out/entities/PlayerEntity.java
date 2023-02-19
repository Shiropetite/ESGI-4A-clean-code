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

    @OneToMany()
    private List<HeroEntity> deck;

    private int tokens;

    public PlayerEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HeroEntity> getDeck() {
        return deck;
    }

    public void setDeck(List<HeroEntity> deck) {
        this.deck = deck;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int numberOfTokens) {
        this.tokens = numberOfTokens;
    }
}
