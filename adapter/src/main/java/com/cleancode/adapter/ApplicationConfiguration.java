package com.cleancode.adapter;

import com.cleancode.adapter.out.components.*;
import com.cleancode.adapter.out.services.*;
import com.cleancode.application.ports.out.*;
import com.cleancode.application.ports.out.repositories.FindHeroDuelsByHero;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public CreateHeroesPersistence createHeroesOut(CreateHeroRefImpl saveHeroRef) {
        return new CreateHeroesPersistenceImpl(saveHeroRef);
    }

    @Bean
    public SearchAvailableHeroesPersistence searchAvailableHeroesOut(FindAllHeroRefImpl findAllHeroRef) {
        return new SearchAvailableHeroesPersistenceImpl(findAllHeroRef);
    }

    @Bean
    public CreatePlayerPersistence createPlayerOut(FindPlayerByNameImpl findPlayerByName, CreatePlayerImpl createPlayer) {
        return new CreatePlayerPersistenceImpl(findPlayerByName, createPlayer);
    }

    @Bean
    public OpenHeroPackPersistence openHeroPackOut(
        FindHeroPackByIdImpl findHeroPackById,
        FindPlayerByIdImpl findPlayerById,
        FindRandomHeroRefByRarityImpl findRandomHeroRefByRarity,
        CreateHeroImpl createHero,
        UpdatePlayerImpl updatePlayer
    ) {
        return new OpenHeroPackPersistenceImpl(
                findHeroPackById,
                findPlayerById,
                findRandomHeroRefByRarity,
                createHero,
                updatePlayer
        );
    }

    @Bean
    public SearchPlayerPersistence searchPlayerOut(FindPlayerByNameImpl findPlayerByName) {
        return new SearchPlayerPersistenceImpl(findPlayerByName);
    }

    @Bean
    public CreateHeroDuelPersistence duelHeroesOut(
        FindPlayerByIdImpl findPlayerById,
        FindHeroByIdImpl findHeroById,
        FindHeroBonusImpl findHeroBonus,
        FindPlayerVictoriesImpl findPlayerVictories,
        UpdateHeroImpl saveHero,
        UpdatePlayerImpl updatePlayer,
        CreateHeroDuelImpl saveHeroDuel
    ) {
        return new CreateHeroDuelPersistenceImpl(
                findPlayerById,
                findHeroById,
                findHeroBonus,
                findPlayerVictories,
                saveHero,
                updatePlayer,
                saveHeroDuel
        );
    }

    @Bean
    public SearchHeroDuelsPersistence searchHeroDuelsOut(FindHeroByIdImpl findHeroById, FindHeroDuelsByHero findHeroDuelsByHero) {
        return new SearchHeroDuelsPersistenceImpl(findHeroById, findHeroDuelsByHero);
    }

}
