package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.mapper.HeroRefMapper;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import com.cleancode.application.ports.out.CreateHeroesPersistence;
import com.cleancode.domain.HeroRef;

public class CreateHeroesPersistenceImpl implements CreateHeroesPersistence {

    private final HeroRefRepository repository;

    public CreateHeroesPersistenceImpl(HeroRefRepository repository) { this.repository = repository; }

    @Override
    public HeroRef create(HeroRef hero) {
        var entity = this.repository.save(HeroRefMapper.get().toEntity(hero));
        return HeroRefMapper.get().toDomain(entity);
    }

}
