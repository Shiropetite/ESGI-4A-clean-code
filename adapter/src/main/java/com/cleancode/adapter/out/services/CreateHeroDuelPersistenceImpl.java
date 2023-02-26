package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.CreateHeroDuelPersistence;
import com.cleancode.application.ports.out.repositories.*;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroBonus;
import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.Player;

import java.util.List;

public final record CreateHeroDuelPersistenceImpl(
    FindPlayerById findPlayerById,
    FindHeroById findHeroById,
    FindPlayerVictories findPlayerVictories,
    FindHeroBonus findHeroBonus,
    UpdatePlayer updatePlayer,
    UpdateHero updateHero,
    CreateHeroDuel createHeroDuel
) implements CreateHeroDuelPersistence {

    @Override
    public Player findPlayerById(Long id) {
        return this.findPlayerById.findPlayerById(id);
    }

    @Override
    public Hero findHeroById(Long id) {
        return this.findHeroById.findHeroById(id);
    }

    @Override
    public List<HeroDuel> findPlayerVictories(Player player) {
        return this.findPlayerVictories.findPlayerVictories(player);
    }

    @Override
    public HeroBonus findHeroBonus(String strongHero, String weakHero) {
        return this.findHeroBonus.findHeroBonus(strongHero, weakHero);
    }

    @Override
    public Player updatePlayer(Player player) {
        return this.updatePlayer.updatePlayer(player);
    }

    @Override
    public Hero updateHero(Hero hero) {
        return this.updateHero.updateHero(hero);
    }

    @Override
    public HeroDuel createHeroDuel(HeroDuel duel) {
        return this.createHeroDuel.createHeroDuel(duel);
    }

}
