package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.adapter.out.mapper.HeroDuelMapper;
import com.cleancode.adapter.out.repositories.HeroDuelRepository;
import com.cleancode.adapter.out.repositories.HeroRepository;
import com.cleancode.application.ports.out.repositories.CreateHeroDuel;
import com.cleancode.domain.HeroDuel;
import org.springframework.stereotype.Component;

@Component
public class CreateHeroDuelImpl implements CreateHeroDuel {

    private final HeroRepository heroRepository;
    private final HeroDuelRepository heroDuelRepository;

    public CreateHeroDuelImpl(HeroRepository heroRepository, HeroDuelRepository heroDuelRepository) {
        this.heroRepository = heroRepository;
        this.heroDuelRepository = heroDuelRepository;
    }

    @Override
    public HeroDuel createHeroDuel(HeroDuel duel) {
        final var winnerEntity = this.heroRepository.findById(duel.getWinner().getId());
        final var loserEntity = this.heroRepository.findById(duel.getLoser().getId());

        if (winnerEntity.isEmpty() || loserEntity.isEmpty()) { return null; }

        final var heroDuelEntity = new HeroDuelEntity();
        heroDuelEntity.setWinner(winnerEntity.get());
        heroDuelEntity.setLoser(loserEntity.get());

        return HeroDuelMapper.get().toDomain(this.heroDuelRepository.save(heroDuelEntity));
    }

}
