package com.cleancode.application.services;

import com.cleancode.application.ports.in.CreateHeroesService;
import com.cleancode.application.ports.out.CreateHeroesPersistence;
import com.cleancode.domain.HeroRef;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateHeroesServiceImpl implements CreateHeroesService {

    private final CreateHeroesPersistence persistence;

    public CreateHeroesServiceImpl(CreateHeroesPersistence persistence) { this.persistence = persistence; }

    public List<HeroRef> create(List<HeroRef> heroes) {
        return this.persistence.create(heroes);
    }

}
