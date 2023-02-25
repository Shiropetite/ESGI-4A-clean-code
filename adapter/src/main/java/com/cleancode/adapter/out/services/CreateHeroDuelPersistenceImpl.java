package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.CreateHeroDuelPersistence;
import com.cleancode.application.ports.out.repositories.*;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroBonus;
import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.Player;

import java.util.List;

public class CreateHeroDuelPersistenceImpl implements CreateHeroDuelPersistence {

    private final FindPlayerById findPlayerById;
    private final FindHeroById findHeroById;
    private final FindHeroBonus findHeroBonus;
    private final FindPlayerVictories findPlayerVictories;
    private final UpdateHero updateHero;
    private final UpdatePlayer updatePlayer;
    private final CreateHeroDuel createHeroDuel;

    public CreateHeroDuelPersistenceImpl(FindPlayerById findPlayerById, FindHeroById findHeroById, FindHeroBonus findHeroBonus, FindPlayerVictories findPlayerVictories, UpdateHero updateHero, UpdatePlayer updatePlayer, CreateHeroDuel createHeroDuel) {
        this.findPlayerById = findPlayerById;
        this.findHeroById = findHeroById;
        this.findHeroBonus = findHeroBonus;
        this.findPlayerVictories = findPlayerVictories;
        this.updateHero = updateHero;
        this.updatePlayer = updatePlayer;
        this.createHeroDuel = createHeroDuel;
    }

    @Override
    public HeroBonus findHeroBonus(String strongHero, String weakHero) {
        return this.findHeroBonus.findHeroBonus(strongHero, weakHero);
    }

    @Override
    public Hero findHeroById(Long id) {
        return this.findHeroById.findHeroById(id);
    }

    @Override
    public Player findPlayerById(Long id) {
        return this.findPlayerById.findPlayerById(id);
    }

    @Override
    public List<HeroDuel> findPlayerVictories(Player player) {
        return this.findPlayerVictories.findPlayerVictories(player);
    }

    @Override
    public Hero updateHero(Hero hero) {
        return this.updateHero.updateHero(hero);
    }

    @Override
    public HeroDuel createHeroDuel(HeroDuel duel) {
        return this.createHeroDuel.createHeroDuel(duel);
    }

    @Override
    public Player updatePlayer(Player player) {
        return this.updatePlayer.updatePlayer(player);
    }
}
