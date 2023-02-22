package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.mapper.HeroBonusMapper;
import com.cleancode.adapter.out.mapper.HeroDuelMapper;
import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.HeroBonusRepository;
import com.cleancode.adapter.out.repositories.HeroDuelRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.application.ports.out.DuelHeroesPersistence;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroBonus;
import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.Player;

import java.util.ArrayList;

public class DuelHeroesPersistenceImpl implements DuelHeroesPersistence {

    private final PlayerRepository playerRepository;
    private final HeroRepository heroRepository;
    private final HeroDuelRepository heroDuelRepository;
    private final HeroBonusRepository heroBonusRepository;

    public DuelHeroesPersistenceImpl(
        PlayerRepository playerRepository,
        HeroRepository heroRepository,
        HeroDuelRepository heroDuelRepository,
        HeroBonusRepository heroBonusRepository
    ) {
        this.playerRepository = playerRepository;
        this.heroRepository = heroRepository;
        this.heroDuelRepository = heroDuelRepository;
        this.heroBonusRepository = heroBonusRepository;
    }

    @Override
    public Player findPlayerById(Long id) {
        return this.playerRepository.findById(id)
            .map(playerEntity -> PlayerMapper.get().toDomain(playerEntity))
            .orElse(null);
    }

    @Override
    public Hero findHeroById(Long id) {
        return this.heroRepository.findById(id)
            .map(heroEntity -> HeroMapper.get().toDomain(heroEntity))
            .orElse(null);
    }

    @Override
    public HeroBonus findHeroBonus(String strongHero, String weakHero) {
        return this.heroBonusRepository.findHeroBonusEntityByStrongAndWeak(strongHero, weakHero)
            .map(heroBonusEntity -> HeroBonusMapper.get().toDomain(heroBonusEntity))
            .orElse(null);
    }

    @Override
    public HeroDuel createHeroDuel(HeroDuel duel) {
        var heroEntity = this.heroRepository.findById(duel.getOpponent().getId());
        if (heroEntity.isPresent()) {
            var heroDuelEntity = HeroDuelMapper.get().toEntity(duel);
            heroDuelEntity.setOpponent(heroEntity.get());
            return HeroDuelMapper.get().toDomain(this.heroDuelRepository.save(heroDuelEntity));
        }
        return null;
    }

    @Override
    public void updateHero(Hero hero) {
        var heroEntity = this.heroRepository.findById(hero.getId());
        if (heroEntity.isPresent()) {
            heroEntity.get().setXp(hero.getXp());
            heroEntity.get().setLevel(hero.getLevel());
            var heroDuelEntity = this.heroDuelRepository.findById(hero.getDuels().get(0).getId());
            heroDuelEntity.ifPresent(duelEntity -> heroEntity.get().getDuels().add(duelEntity));
            this.heroRepository.save(heroEntity.get());
        }
    }

}
