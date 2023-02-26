package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.application.ports.out.repositories.FindHeroById;
import com.cleancode.domain.Hero;
import org.springframework.stereotype.Component;

@Component
public final class FindHeroByIdImpl implements FindHeroById {

    private final HeroRepository heroRepository;

    public FindHeroByIdImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public final Hero findHeroById(Long id) {
        return this.heroRepository.findById(id)
            .map(heroEntity -> HeroMapper.get().toDomain(heroEntity))
            .orElse(null);
    }
    
}
