package com.cleancode.application.services;

import com.cleancode.application.ports.in.SearchHeroDuelsService;
import com.cleancode.application.ports.out.SearchHeroDuelsPersistence;
import com.cleancode.domain.HeroDuel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class SearchHeroDuelsServiceImpl implements SearchHeroDuelsService {

    private final SearchHeroDuelsPersistence persistence;

    public SearchHeroDuelsServiceImpl(SearchHeroDuelsPersistence persistence) { this.persistence = persistence; }

    public final List<HeroDuel> search(Long id) {
        final var hero = this.persistence.findHeroById(id);
        if (hero == null) {
            throw new RuntimeException("Le héros " + id + " n'existe pas");
        }
        return this.persistence.findHeroDuelsByHero(hero);
    }

}
