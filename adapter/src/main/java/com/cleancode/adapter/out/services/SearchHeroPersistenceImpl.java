package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.application.ports.out.SearchHeroPersistence;
import com.cleancode.domain.Hero;

public class SearchHeroPersistenceImpl implements SearchHeroPersistence {

    private final HeroRepository repository;

    public SearchHeroPersistenceImpl(HeroRepository repository) { this.repository = repository; }

    @Override
    public Hero findById(Long id) {
        return this.repository.findById(id)
            .map(heroEntity -> HeroMapper.get().toDomain(heroEntity))
            .orElse(null);
    }

}
