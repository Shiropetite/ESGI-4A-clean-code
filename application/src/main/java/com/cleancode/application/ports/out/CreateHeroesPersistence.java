package com.cleancode.application.ports.out;

import com.cleancode.domain.RefHero;

import java.util.List;

public interface CreateHeroesPersistence {

    List<RefHero> create(List<RefHero> heroes);

}
