package com.cleancode.application.ports.out.repositories;

import com.cleancode.domain.HeroBonus;

public interface FindHeroBonus {

    HeroBonus findHeroBonus(String strongHero, String weakHero);

}
