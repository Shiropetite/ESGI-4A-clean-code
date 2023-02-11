package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.mapper.HeroRefMapper;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import com.cleancode.application.ports.out.SearchAvailableHeroesPersistence;
import com.cleancode.domain.HeroRef;

import java.util.List;
import java.util.stream.Collectors;

public class SearchAvailableHeroesPersistenceImpl implements SearchAvailableHeroesPersistence {

    private final HeroRefRepository repository;

    public SearchAvailableHeroesPersistenceImpl(HeroRefRepository repository) { this.repository = repository; }

    @Override
    public List<HeroRef> search() {
        return this.repository.findAll()
            .stream()
            .map(heroRefEntity -> HeroRefMapper.get().toDomain(heroRefEntity))
            .collect(Collectors.toList());
    }

}
