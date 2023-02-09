package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.repositories.RefHeroRepository;
import com.cleancode.application.ports.out.CreateHeroesPersistence;
import com.cleancode.domain.RefHero;

import java.util.List;

import static com.cleancode.adapter.out.mapper.RefHeroEntityMapper.fromDomain;

public class CreateHeroesPersistenceImpl implements CreateHeroesPersistence {

    private final RefHeroRepository repository;

    public CreateHeroesPersistenceImpl(RefHeroRepository repository) { this.repository = repository; }

    @Override
    public List<RefHero> create(List<RefHero> heroes) {
        for (RefHero hero: heroes) {
            System.out.println(hero.getMaxHealthPoints());
            this.repository.save(fromDomain(hero));
        }
        return heroes;
    }

}
