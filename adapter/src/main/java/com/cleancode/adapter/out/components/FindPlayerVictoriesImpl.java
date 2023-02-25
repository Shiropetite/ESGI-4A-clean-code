package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.mapper.HeroDuelMapper;
import com.cleancode.adapter.out.repositories.HeroDuelRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.application.ports.out.repositories.FindPlayerVictories;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindPlayerVictoriesImpl implements FindPlayerVictories {

    private final HeroRepository heroRepository;
    private final HeroDuelRepository heroDuelRepository;

    public FindPlayerVictoriesImpl(HeroRepository heroRepository, HeroDuelRepository heroDuelRepository) {
        this.heroRepository = heroRepository;
        this.heroDuelRepository = heroDuelRepository;
    }

    @Override
    public List<HeroDuel> findPlayerVictories(Player player) {
        final var duels = new ArrayList<HeroDuelEntity>();

        for (Hero hero: player.getDeck()) {
            final var heroEntity = this.heroRepository.findById(hero.getId());
            if (heroEntity.isEmpty()) { break; }
            final var duelEntities =
                    this.heroDuelRepository.findHeroDuelEntitiesByWinner(heroEntity.get());
            if (duelEntities.isEmpty()) { break; }
            duels.addAll(duelEntities.get());
        }

        return HeroDuelMapper.get().toDomain(duels);
    }

}
