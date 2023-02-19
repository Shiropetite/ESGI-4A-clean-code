package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.mapper.HeroPackMapper;
import com.cleancode.adapter.out.mapper.HeroRefMapper;
import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.HeroPackRepository;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.application.ports.out.OpenHeroPackPersistence;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroPack;
import com.cleancode.domain.HeroRef;
import com.cleancode.domain.Player;

import java.util.Random;

public class OpenHeroPackPersistenceImpl implements OpenHeroPackPersistence {

    private final PlayerRepository playerRepository;
    private final HeroPackRepository heroPackRepository;
    private final HeroRefRepository heroRefRepository;
    private final HeroRepository heroRepository;

    public OpenHeroPackPersistenceImpl(
        PlayerRepository playerRepository,
        HeroPackRepository heroPackRepository,
        HeroRefRepository heroRefRepository,
        HeroRepository heroRepository
    ) {
        this.playerRepository = playerRepository;
        this.heroPackRepository = heroPackRepository;
        this.heroRefRepository = heroRefRepository;
        this.heroRepository = heroRepository;
    }

    @Override
    public Player findPlayerById(Long id) {
        if (this.playerRepository.findById(id).isPresent()) {
            return PlayerMapper.get().toDomain(this.playerRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public HeroPack findHeroPackById(Long id) {
        if (this.heroPackRepository.findById(id).isPresent()) {
            return HeroPackMapper.get().toDomain(this.heroPackRepository.findById(id).get());
        }
        return null;
    }

    @Override
    public HeroRef findRandomHeroRefByRarity(String heroRarityName) {
        var heroesByRarity = HeroRefMapper.get().toDomain(this.heroRefRepository.findByRarity(heroRarityName));
        return heroesByRarity.get(new Random().nextInt(heroesByRarity.size()));
    }

    @Override
    public Hero create(Hero hero) {
        var entity = HeroMapper.get().toEntity(hero);
        var heroRefEntity = this.heroRefRepository.findById(hero.getRef().getId());

        if (heroRefEntity.isPresent()) {
            entity.setRef(heroRefEntity.get());
            var saveEntity = this.heroRepository.save(entity);
            return HeroMapper.get().toDomain(saveEntity);
        }

        return null;
    }

}
