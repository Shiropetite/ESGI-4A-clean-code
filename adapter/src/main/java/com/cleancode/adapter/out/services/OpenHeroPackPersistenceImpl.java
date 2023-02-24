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
        var heroRefEntities = this.heroRefRepository.findHeroRefEntityByRarity(heroRarityName);
        if (heroRefEntities.isEmpty()) { return null; }
        var heroRefs = HeroRefMapper.get().toDomain(heroRefEntities.get());
        return heroRefs.get(new Random().nextInt(heroRefs.size()));
    }

    @Override
    public Hero createHero(Hero hero) {
        var heroRefEntity = this.heroRefRepository.findById(hero.getRef().getId());
        if (heroRefEntity.isEmpty()) { return null; }

        var heroEntity = HeroMapper.get().toEntity(hero);
        heroEntity.setRef(heroRefEntity.get());
        return HeroMapper.get().toDomain(this.heroRepository.save(heroEntity));
    }

    @Override
    public void updatePlayer(Player player) {
        var playerEntity = this.playerRepository.findById(player.getId());
        if (playerEntity.isEmpty()) { return; }

        playerEntity.get().setTokens(player.getTokens());
        for (Hero hero : player.getDeck()) {
            var heroEntity = this.heroRepository.findById(hero.getId());
            if (heroEntity.isEmpty()) { break; }
            if (!(playerEntity.get().getDeck().contains(heroEntity.get()))) {
                playerEntity.get().getDeck().add(heroEntity.get());
            }
        }
        this.playerRepository.save(playerEntity.get());
    }

}
