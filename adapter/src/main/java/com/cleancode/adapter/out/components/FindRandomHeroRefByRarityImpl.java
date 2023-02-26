package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.HeroRefMapper;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import com.cleancode.application.ports.out.repositories.FindRandomHeroRefByRarity;
import com.cleancode.domain.HeroRef;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public final class FindRandomHeroRefByRarityImpl implements FindRandomHeroRefByRarity {

    private final HeroRefRepository heroRefRepository;

    public FindRandomHeroRefByRarityImpl(HeroRefRepository heroRefRepository) {
        this.heroRefRepository = heroRefRepository;
    }

    @Override
    public final HeroRef findRandomHeroRefByRarity(String heroRarityName) {
        final var heroRefEntities = this.heroRefRepository.findHeroRefEntityByRarity(heroRarityName);
        if (heroRefEntities.isEmpty()) { return null; }
        final var heroRefs = HeroRefMapper.get().toDomain(heroRefEntities.get());
        return heroRefs.get(new Random().nextInt(heroRefs.size()));
    }

}
