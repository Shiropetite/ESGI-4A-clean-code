package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.OpenHeroPackPersistence;
import com.cleancode.application.ports.out.repositories.*;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroPack;
import com.cleancode.domain.HeroRef;
import com.cleancode.domain.Player;

public record OpenHeroPackPersistenceImpl(
    FindPlayerById findPlayerById,
    FindHeroPackById findHeroPackById,
    FindRandomHeroRefByRarity findRandomHeroRefByRarity,
    CreateHero createHero,
    UpdatePlayer updatePlayer
) implements OpenHeroPackPersistence {

    @Override
    public Player findPlayerById(Long id) {
        return this.findPlayerById.findPlayerById(id);
    }

    @Override
    public HeroPack findHeroPackById(Long id) {
        return this.findHeroPackById.findHeroPackById(id);
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
