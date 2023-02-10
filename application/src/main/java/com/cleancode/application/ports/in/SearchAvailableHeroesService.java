package com.cleancode.application.ports.in;

import com.cleancode.domain.RefHero;

import java.util.List;

public interface SearchAvailableHeroesService {

    List<RefHero> search();

}
