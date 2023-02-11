package com.cleancode.application.ports.in;

import com.cleancode.domain.HeroRef;

import java.util.List;

public interface SearchAvailableHeroesService {

    List<HeroRef> search();

}
