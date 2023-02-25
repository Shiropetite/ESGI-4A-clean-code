package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.SearchAvailableHeroesPersistence;
import com.cleancode.application.ports.out.repositories.FindAllHeroRef;
import com.cleancode.domain.HeroRef;

import java.util.List;

public class SearchAvailableHeroesPersistenceImpl implements SearchAvailableHeroesPersistence {

    private final FindAllHeroRef findAllHeroRef;

    public SearchAvailableHeroesPersistenceImpl(FindAllHeroRef findAllHeroRef) {
        this.findAllHeroRef = findAllHeroRef;
    }

    @Override
    public List<HeroRef> findAllHeroRef() {
        return this.findAllHeroRef.findAllHeroRef();
    }
}
