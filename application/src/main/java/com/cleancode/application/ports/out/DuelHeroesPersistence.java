package com.cleancode.application.ports.out;

import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroBonus;
import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.Player;

import java.util.List;

public interface DuelHeroesPersistence {

    Player findPlayerById(Long id);

    Hero findHeroById(Long id);

    HeroBonus findHeroBonus(String strongHero, String weakHero);

    HeroDuel save(HeroDuel duel);

    void updatePlayer(Player player);

    List<HeroDuel> getWinDuels(Player player);

}
