package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.HeroBonusMapper;
import com.cleancode.adapter.out.repositories.HeroBonusRepository;
import com.cleancode.application.ports.out.repositories.FindHeroBonus;
import com.cleancode.domain.HeroBonus;
import org.springframework.stereotype.Component;

@Component
public final class FindHeroBonusImpl implements FindHeroBonus {

    private final HeroBonusRepository heroBonusRepository;

    public FindHeroBonusImpl(HeroBonusRepository heroBonusRepository) {
        this.heroBonusRepository = heroBonusRepository;
    }

    @Override
    public final HeroBonus findHeroBonus(String strongHero, String weakHero) {
        return this.heroBonusRepository.findHeroBonusEntityByStrongAndWeak(strongHero, weakHero)
            .map(heroBonusEntity -> HeroBonusMapper.get().toDomain(heroBonusEntity))
            .orElse(null);
    }

}
