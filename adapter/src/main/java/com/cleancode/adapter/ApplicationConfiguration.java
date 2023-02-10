package com.cleancode.adapter;

import com.cleancode.adapter.out.repositories.RefHeroRepository;
import com.cleancode.adapter.out.services.CreateHeroesPersistenceImpl;
import com.cleancode.adapter.out.services.SearchAvailableHeroesPersistenceImpl;
import com.cleancode.application.ports.out.CreateHeroesPersistence;
import com.cleancode.application.ports.out.SearchAvailableHeroesPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public CreateHeroesPersistence createHeroesOut(RefHeroRepository refHeroRepository) {
        return new CreateHeroesPersistenceImpl(refHeroRepository);
    }

    @Bean
    public SearchAvailableHeroesPersistence searchAvailableHeroesOut(RefHeroRepository refHeroRepository) {
        return new SearchAvailableHeroesPersistenceImpl(refHeroRepository);
    }

}
