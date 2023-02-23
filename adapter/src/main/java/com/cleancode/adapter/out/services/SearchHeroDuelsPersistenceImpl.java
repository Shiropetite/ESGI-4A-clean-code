package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.mapper.HeroDuelMapper;
import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroDuelRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.application.ports.out.SearchHeroDuelsPersistence;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;

import java.util.ArrayList;
import java.util.List;

public class SearchHeroDuelsPersistenceImpl implements SearchHeroDuelsPersistence {

    private final HeroRepository heroRepository;
    private final HeroDuelRepository heroDuelRepository;

    public SearchHeroDuelsPersistenceImpl(HeroRepository heroRepository, HeroDuelRepository heroDuelRepository) {
        this.heroRepository = heroRepository;
        this.heroDuelRepository = heroDuelRepository;
    }

    @Override
    public Hero findById(Long id) {
        return this.heroRepository.findById(id)
            .map(heroEntity -> HeroMapper.get().toDomain(heroEntity))
            .orElse(null);
    }

    @Override
    public List<HeroDuel> findHeroDuels(Hero hero) {
        var heroEntity = this.heroRepository.findById(hero.getId());
        if (heroEntity.isEmpty()) { return null; }

        var winnerDuels = this.heroDuelRepository.findHeroDuelEntitiesByWinner(heroEntity.get());
        var loserDuels = this.heroDuelRepository.findHeroDuelEntitiesByLoser(heroEntity.get());

        var mergedList = new ArrayList<HeroDuelEntity>();
        winnerDuels.ifPresent(mergedList::addAll);
        loserDuels.ifPresent(mergedList::addAll);

        return mergedList.stream().map(entity -> HeroDuelMapper.get().toDomain(entity)).toList();
    }

}
