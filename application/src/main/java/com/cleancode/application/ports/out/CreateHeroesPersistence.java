package com.cleancode.application.ports.out;

import com.cleancode.domain.HeroRef;

public interface CreateHeroesPersistence {

    HeroRef create(HeroRef hero);

}
