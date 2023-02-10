package com.cleancode.application.ports.out;

import com.cleancode.domain.RefHero;

import java.util.List;

public interface SearchAvailableHeroesPersistence {

    List<RefHero> search();

}
