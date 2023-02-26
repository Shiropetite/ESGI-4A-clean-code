package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.mapper.HeroDuelMapper;
import com.cleancode.adapter.out.repositories.HeroDuelRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.application.ports.out.repositories.FindHeroDuelsByHero;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public final class FindHeroDuelsByHeroImpl implements FindHeroDuelsByHero {

    private final HeroRepository heroRepository;
    private final HeroDuelRepository heroDuelRepository;

    public FindHeroDuelsByHeroImpl(HeroRepository heroRepository, HeroDuelRepository heroDuelRepository) {
        this.heroRepository = heroRepository;
        this.heroDuelRepository = heroDuelRepository;
    }

    @Override
    public final List<HeroDuel> findHeroDuelsByHero(Hero hero) {
        final var heroEntity = this.heroRepository.findById(hero.getId());
        if (heroEntity.isEmpty()) { return null; }

        final var winnerDuels = this.heroDuelRepository.findHeroDuelEntitiesByWinner(heroEntity.get());
        final var loserDuels = this.heroDuelRepository.findHeroDuelEntitiesByLoser(heroEntity.get());

        final var mergedList = new ArrayList<HeroDuelEntity>();
        winnerDuels.ifPresent(mergedList::addAll);
        loserDuels.ifPresent(mergedList::addAll);

        return mergedList.stream().map(entity -> HeroDuelMapper.get().toDomain(entity)).toList();
    }

}
