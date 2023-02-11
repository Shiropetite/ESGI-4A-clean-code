package com.cleancode.application.services;

import com.cleancode.application.ports.in.SearchAvailableHeroesService;
import com.cleancode.application.ports.out.SearchAvailableHeroesPersistence;
import com.cleancode.domain.HeroRef;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchAvailableHeroesServiceImpl implements SearchAvailableHeroesService {

    private final SearchAvailableHeroesPersistence persistence;

    public SearchAvailableHeroesServiceImpl(SearchAvailableHeroesPersistence persistence) { this.persistence = persistence; }

    public List<HeroRef> search() {
        return this.persistence.search();
    }
}
