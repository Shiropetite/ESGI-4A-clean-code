package com.cleancode.adapter;

import com.cleancode.adapter.out.repositories.HeroPackRepository;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.adapter.out.repositories.PlayerRepository;
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
        return new OpenHeroPackPersistenceImpl(playerRepository, heroPackRepository, heroRefRepository, heroRepository);
    }

    @Bean
    public SearchPlayerPersistence searchPlayerOut(PlayerRepository playerRepository) {
        return new SearchPlayerPersistenceImpl(playerRepository);
    }

}
