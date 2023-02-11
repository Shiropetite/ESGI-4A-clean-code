package com.cleancode.application.ports.in;

import com.cleancode.domain.HeroRef;

import java.util.List;

public interface CreateHeroesService {

    List<HeroRef> create(List<HeroRef> heroes);

}
