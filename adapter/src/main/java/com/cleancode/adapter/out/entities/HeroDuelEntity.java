package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "HERO_DUEL")
public class HeroDuelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private HeroEntity winner;

    @ManyToOne()
    private HeroEntity loser;

    public HeroDuelEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HeroEntity getWinner() {
        return winner;
    }

    public void setWinner(HeroEntity winner) {
        this.winner = winner;
    }

    public HeroEntity getLoser() {
        return loser;
    }

    public void setLoser(HeroEntity loser) {
        this.loser = loser;
    }
}
