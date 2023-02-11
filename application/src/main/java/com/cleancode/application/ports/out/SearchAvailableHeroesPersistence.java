package com.cleancode.application.ports.out;

import com.cleancode.domain.HeroRef;

import java.util.List;

public interface SearchAvailableHeroesPersistence {

    List<HeroRef> search();

}
