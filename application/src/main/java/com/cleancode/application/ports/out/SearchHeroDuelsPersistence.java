package com.cleancode.application.ports.out;

import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;

import java.util.List;

public interface SearchHeroDuelsPersistence {

    Hero findById(Long id);

    List<HeroDuel> findHeroDuels(Hero hero);

}