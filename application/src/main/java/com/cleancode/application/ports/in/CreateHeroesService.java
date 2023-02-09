package com.cleancode.application.ports.in;

import com.cleancode.domain.RefHero;

import java.util.List;

public interface CreateHeroesService {

    List<RefHero> create(List<RefHero> heroes);

}
