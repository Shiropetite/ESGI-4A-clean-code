package com.cleancode.adapter.out;

import jakarta.persistence.*;

@Entity
@Table(name = "HERO_BONUS")
public class HeroBonusEntity {

    @Id
    private String id;

    private String strong;

    private String weak;

    private int bonus;

}
