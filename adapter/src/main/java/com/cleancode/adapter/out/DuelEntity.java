package com.cleancode.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DUEL")
public class DuelEntity {

    @Id
    private String id;

    @ManyToOne()
    private PlayerEntity enemy;

   private boolean victory;

    public DuelEntity() {}

}
