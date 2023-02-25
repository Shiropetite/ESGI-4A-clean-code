package com.cleancode.application.ports.out.repositories;

import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;

import java.util.List;

public interface FindHeroDuelsByHero {

    List<HeroDuel> findHeroDuelsByHero(Hero hero);

}
