package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.CreateHeroesPersistence;
import com.cleancode.application.ports.out.repositories.CreateHeroRef;
import com.cleancode.domain.HeroRef;

public class CreateHeroesPersistenceImpl implements CreateHeroesPersistence {

    private final CreateHeroRef createHeroRef;

    public CreateHeroesPersistenceImpl(CreateHeroRef createHeroRef) {
        this.createHeroRef = createHeroRef;
    }

    @Override
    public HeroRef createHeroRef(HeroRef hero) {
        return this.createHeroRef.createHeroRef(hero);
    }

}
