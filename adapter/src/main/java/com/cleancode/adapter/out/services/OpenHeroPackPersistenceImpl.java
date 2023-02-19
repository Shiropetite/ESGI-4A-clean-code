package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.entities.HeroEntity;
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

import java.util.ArrayList;
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
        return this.playerRepository.findById(id)
            .map(playerEntity -> PlayerMapper.get().toDomain(playerEntity))
            .orElse(null);
    }

    @Override
    public HeroPack findHeroPackById(Long id) {
        return this.heroPackRepository.findById(id)
            .map(heroPackEntity -> HeroPackMapper.get().toDomain(heroPackEntity))
            .orElse(null);
    }

    @Override
    public HeroRef findRandomHeroRefByRarity(String heroRarityName) {
        var heroesByRarity = HeroRefMapper.get().toDomain(this.heroRefRepository.findByRarity(heroRarityName));
        return heroesByRarity.get(new Random().nextInt(heroesByRarity.size()));
    }

    @Override
    public Hero createHero(Hero hero) {
        var heroRefEntity = this.heroRefRepository.findById(hero.getRef().getId());
        if (heroRefEntity.isPresent()) {
            var heroEntity = HeroMapper.get().toEntity(hero);
            heroEntity.setRef(heroRefEntity.get());
            return HeroMapper.get().toDomain(this.heroRepository.save(heroEntity));
        }
        return null;
    }

    @Override
    public void savePlayerDeck(Player player) {
        var playerEntity = this.playerRepository.findById(player.getId());
        if (playerEntity.isPresent()) {
            var heroEntities = new ArrayList<HeroEntity>();
            for (Hero hero : player.getDeck()) {
                var heroEntity = this.heroRepository.findById(hero.getId());
                heroEntity.ifPresent(heroEntities::add);
            }
            playerEntity.get().setDeck(heroEntities);
            this.playerRepository.save(playerEntity.get());
        }
    }

}
