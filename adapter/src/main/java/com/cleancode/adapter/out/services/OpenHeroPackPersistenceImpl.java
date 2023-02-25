package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.OpenHeroPackPersistence;
import com.cleancode.application.ports.out.repositories.*;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroPack;
import com.cleancode.domain.HeroRef;
import com.cleancode.domain.Player;

public class OpenHeroPackPersistenceImpl implements OpenHeroPackPersistence {

    private final FindHeroPackById findHeroPackById;
    private final FindPlayerById findPlayerById;
    private final FindRandomHeroRefByRarity findRandomHeroRefByRarity;
    private final CreateHero createHero;
    private final UpdatePlayer updatePlayer;

    public OpenHeroPackPersistenceImpl(FindHeroPackById findHeroPackById, FindPlayerById findPlayerById, FindRandomHeroRefByRarity findRandomHeroRefByRarity, CreateHero createHero, UpdatePlayer updatePlayer) {
        this.findHeroPackById = findHeroPackById;
        this.findPlayerById = findPlayerById;
        this.findRandomHeroRefByRarity = findRandomHeroRefByRarity;
        this.createHero = createHero;
        this.updatePlayer = updatePlayer;
    }

    @Override
    public HeroPack findHeroPackById(Long id) {
        return this.findHeroPackById.findHeroPackById(id);
    }

    @Override
    public Player findPlayerById(Long id) {
        return this.findPlayerById.findPlayerById(id);
    }

    @Override
    public HeroRef findRandomHeroRefByRarity(String heroRarityName) {
        return this.findRandomHeroRefByRarity.findRandomHeroRefByRarity(heroRarityName);
    }

    @Override
    public Hero createHero(Hero hero) {
        return this.createHero.createHero(hero);
    }

    @Override
    public Player updatePlayer(Player player) {
        return this.updatePlayer.updatePlayer(player);
    }
}
