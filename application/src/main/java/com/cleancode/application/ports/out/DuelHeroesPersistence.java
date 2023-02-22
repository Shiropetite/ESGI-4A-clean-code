package com.cleancode.application.ports.out;

import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroBonus;
import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.Player;

public interface DuelHeroesPersistence {

    Player findPlayerById(Long id);

    Hero findHeroById(Long id);

    HeroBonus findHeroBonus(String strongHero, String weakHero);

    HeroDuel createHeroDuel(HeroDuel duel);

    void updateHero(Hero hero);

}
