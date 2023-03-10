package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "HERO")
public final class HeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private HeroRefEntity ref;

    private int xp;

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

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroEntity that = (HeroEntity) o;
        return Objects.equals(id, that.id);
    }

}
