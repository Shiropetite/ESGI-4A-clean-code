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
import com.cleancode.application.ports.out.CreateHeroDuelPersistence;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroBonus;
import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.Player;

import java.util.ArrayList;
import java.util.List;

public class CreateHeroDuelPersistenceImpl implements CreateHeroDuelPersistence {

    private final PlayerRepository playerRepository;
    private final HeroRepository heroRepository;
    private final HeroDuelRepository heroDuelRepository;
    private final HeroBonusRepository heroBonusRepository;

    public CreateHeroDuelPersistenceImpl(
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
    public HeroDuel save(HeroDuel duel) {
        var winnerEntity = this.heroRepository.findById(duel.getWinner().getId());
        var loserEntity = this.heroRepository.findById(duel.getLoser().getId());

        if (winnerEntity.isEmpty() || loserEntity.isEmpty()) { return null; }

        var heroDuelEntity = new HeroDuelEntity();
        heroDuelEntity.setWinner(winnerEntity.get());
        heroDuelEntity.setLoser(loserEntity.get());

        return HeroDuelMapper.get().toDomain(this.heroDuelRepository.save(heroDuelEntity));
    }

    @Override
    public List<HeroDuel> findVictories(Player player) {
        var duels = new ArrayList<HeroDuelEntity>();

        for (Hero hero: player.getDeck()) {
            var heroEntity = this.heroRepository.findById(hero.getId());
            if (heroEntity.isEmpty()) { break; }
            var duelEntities =
                    this.heroDuelRepository.findHeroDuelEntitiesByWinner(heroEntity.get());
            if (duelEntities.isEmpty()) { break; }
            duels.addAll(duelEntities.get());
        }

        return HeroDuelMapper.get().toDomain(duels);
    }

    @Override
    public void updateHero(Hero hero) {
        var heroEntity = this.heroRepository.findById(hero.getId());
        if (heroEntity.isEmpty()) { return; }

        heroEntity.get().setXp(hero.getXp());
        heroEntity.get().setLevel(hero.getLevel());
        this.heroRepository.save(heroEntity.get());
    }

    @Override
    public void updatePlayer(Player player) {
        var playerEntity = this.playerRepository.findById(player.getId());
        if (playerEntity.isEmpty()) { return; }

        playerEntity.get().setTokens(player.getTokens());
        this.playerRepository.save(playerEntity.get());
    }

}
