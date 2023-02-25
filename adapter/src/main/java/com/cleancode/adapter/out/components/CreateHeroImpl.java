package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.application.ports.out.repositories.CreateHero;
import com.cleancode.domain.Hero;
import org.springframework.stereotype.Component;

@Component
public class CreateHeroImpl implements CreateHero {

    private final HeroRepository heroRepository;

    public CreateHeroImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public Hero createHero(Hero hero) {
        return HeroMapper.get().toDomain(this.heroRepository.save(HeroMapper.get().toEntity(hero)));
    }
}
