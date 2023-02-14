package com.cleancode.adapter;

import com.cleancode.adapter.out.repositories.HeroPackRepository;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.adapter.out.services.CreateHeroesPersistenceImpl;
import com.cleancode.adapter.out.services.CreatePlayerPersistenceImpl;
import com.cleancode.adapter.out.services.OpenHeroPackPersistenceImpl;
import com.cleancode.adapter.out.services.SearchAvailableHeroesPersistenceImpl;
import com.cleancode.application.ports.out.CreateHeroesPersistence;
import com.cleancode.application.ports.out.CreatePlayerPersistence;
import com.cleancode.application.ports.out.OpenHeroPackPersistence;
import com.cleancode.application.ports.out.SearchAvailableHeroesPersistence;
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
        HeroRefRepository heroRefRepository
    ) {
        return new OpenHeroPackPersistenceImpl(playerRepository, heroPackRepository, heroRefRepository);
    }

}
