package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.mapper.RefHeroEntityMapper;
import com.cleancode.adapter.out.repositories.RefHeroRepository;
import com.cleancode.application.ports.out.SearchAvailableHeroesPersistence;
import com.cleancode.domain.RefHero;

import java.util.List;
import java.util.stream.Collectors;

public class SearchAvailableHeroesPersistenceImpl implements SearchAvailableHeroesPersistence {

    private final RefHeroRepository repository;

    public SearchAvailableHeroesPersistenceImpl(RefHeroRepository repository) { this.repository = repository; }

    @Override
    public List<RefHero> search() {
        return this.repository.findAll().stream().map(RefHeroEntityMapper::toDomain).collect(Collectors.toList());
    }

}
