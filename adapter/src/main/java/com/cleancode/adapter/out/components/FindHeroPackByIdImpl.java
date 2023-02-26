package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.HeroPackMapper;
import com.cleancode.adapter.out.repositories.HeroPackRepository;
import com.cleancode.application.ports.out.repositories.FindHeroPackById;
import com.cleancode.domain.HeroPack;
import org.springframework.stereotype.Component;

@Component
public final class FindHeroPackByIdImpl implements FindHeroPackById {

    private final HeroPackRepository heroPackRepository;

    public FindHeroPackByIdImpl(HeroPackRepository heroPackRepository) {
        this.heroPackRepository = heroPackRepository;
    }

    @Override
    public final HeroPack findHeroPackById(Long id) {
        return this.heroPackRepository.findById(id)
            .map(heroPackEntity -> HeroPackMapper.get().toDomain(heroPackEntity))
            .orElse(null);
    }

}
