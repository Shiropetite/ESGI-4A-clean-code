package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.CreateHeroesPersistence;
import com.cleancode.application.ports.out.repositories.CreateHeroRef;
import com.cleancode.domain.HeroRef;

public final record CreateHeroesPersistenceImpl(CreateHeroRef createHeroRef) implements CreateHeroesPersistence {

    @Override
    public HeroRef createHeroRef(HeroRef hero) {
        return this.createHeroRef.createHeroRef(hero);
    }

}
