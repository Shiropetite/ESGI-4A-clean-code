package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.HeroRefMapper;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import com.cleancode.application.ports.out.repositories.CreateHeroRef;
import com.cleancode.domain.HeroRef;
import org.springframework.stereotype.Component;

@Component
public final class CreateHeroRefImpl implements CreateHeroRef {

    private final HeroRefRepository repository;

    public CreateHeroRefImpl(HeroRefRepository repository) { this.repository = repository; }

    @Override
    public final HeroRef createHeroRef(HeroRef hero) {
        return HeroRefMapper.get().toDomain(this.repository.save(HeroRefMapper.get().toEntity(hero)));
    }

}
