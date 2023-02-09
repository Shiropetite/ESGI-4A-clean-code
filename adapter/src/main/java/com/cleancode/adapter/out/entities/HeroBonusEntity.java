package com.cleancode.adapter.out.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "HERO_BONUS")
public class HeroBonusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String strong;

    private String weak;

    private int bonus;

}
