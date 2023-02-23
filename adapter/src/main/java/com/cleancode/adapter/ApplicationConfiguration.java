package com.cleancode.adapter;

import com.cleancode.adapter.out.repositories.*;
import com.cleancode.adapter.out.services.*;
import com.cleancode.application.ports.out.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public CreateHeroesPersistence createHeroesOut(HeroRefRepository refHeroRepository) {
        return new CreateHeroesPersistenceImpl(refHeroRepository);
    }

    @Bean
    public SearchAvailableHeroesPersistence searchAvailableHeroesOut(HeroRefRepository refHeroRepository) {
        return new SearchAvailableHeroesPersistenceImpl(refHeroRepository);
    }

    @Bean
    public CreatePlayerPersistence createPlayerOut(PlayerRepository playerRepository) {
        return new CreatePlayerPersistenceImpl(playerRepository);
    }

    @Bean
    public OpenHeroPackPersistence openHeroPackOut(
        PlayerRepository playerRepository,
        HeroPackRepository heroPackRepository,
        HeroRefRepository heroRefRepository,
        HeroRepository heroRepository
    ) {
        return new OpenHeroPackPersistenceImpl(
            playerRepository,
            heroPackRepository,
            heroRefRepository,
            heroRepository
        );
    }

    @Bean
    public SearchPlayerPersistence searchPlayerOut(PlayerRepository playerRepository) {
        return new SearchPlayerPersistenceImpl(playerRepository);
    }

    @Bean
    public DuelHeroesPersistence duelHeroesOut(
        PlayerRepository playerRepository,
        HeroRepository heroRepository,
        HeroDuelRepository heroDuelRepository,
        HeroBonusRepository heroBonusRepository
    ) {
        return new DuelHeroesPersistenceImpl(
            playerRepository,
            heroRepository,
            heroDuelRepository,
            heroBonusRepository
        );
    }

    @Bean
    public SearchHeroDuelsPersistence searchHeroDuelsOut(HeroRepository heroRepository, HeroDuelRepository heroDuelRepository) {
        return new SearchHeroDuelsPersistenceImpl(heroRepository, heroDuelRepository);
    }

}
