package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.SearchHeroDuelsPersistence;
import com.cleancode.application.ports.out.repositories.FindHeroById;
import com.cleancode.application.ports.out.repositories.FindHeroDuelsByHero;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;

import java.util.List;

public final record SearchHeroDuelsPersistenceImpl(
    FindHeroById findHeroById,
    FindHeroDuelsByHero findHeroDuelsByHero
) implements SearchHeroDuelsPersistence {

    @Override
    public Hero findHeroById(Long id) {
        return this.findHeroById.findHeroById(id);
    }

    @Override
    public List<HeroDuel> findHeroDuelsByHero(Hero hero) {
        return this.findHeroDuelsByHero.findHeroDuelsByHero(hero);
    }

}
