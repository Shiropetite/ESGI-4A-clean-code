package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.application.ports.out.repositories.UpdateHero;
import com.cleancode.domain.Hero;
import org.springframework.stereotype.Component;

@Component
public final class UpdateHeroImpl implements UpdateHero {

    private final HeroRepository heroRepository;

    public UpdateHeroImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public final Hero updateHero(Hero hero) {
        final var heroEntity = this.heroRepository.findById(hero.getId());
        if (heroEntity.isEmpty()) { return null; }

        heroEntity.get().setXp(hero.getXp());
        heroEntity.get().setLevel(hero.getLevel());

        final var entity = this.heroRepository.save(heroEntity.get());
        return HeroMapper.get().toDomain(entity);
    }

}
