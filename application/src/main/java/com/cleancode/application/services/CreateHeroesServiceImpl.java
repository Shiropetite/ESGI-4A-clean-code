package com.cleancode.application.services;

import com.cleancode.application.ports.in.CreateHeroesService;
import com.cleancode.application.ports.out.CreateHeroesPersistence;
import com.cleancode.domain.HeroRef;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class CreateHeroesServiceImpl implements CreateHeroesService {

    private final CreateHeroesPersistence persistence;

    public CreateHeroesServiceImpl(CreateHeroesPersistence persistence) { this.persistence = persistence; }

    public final List<HeroRef> create(List<HeroRef> heroes) {
        final var saveHeroes = new ArrayList<HeroRef>();

        for (HeroRef hero: heroes) {
            hero.applyRarityFactor();
            final var saveHero = this.persistence.createHeroRef(hero);
            saveHeroes.add(saveHero);
        }

        return saveHeroes;
    }

}
