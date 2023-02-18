package com.cleancode.application.ports.out;

import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroPack;
import com.cleancode.domain.HeroRef;
import com.cleancode.domain.Player;

public interface OpenHeroPackPersistence {

    Player findPlayerById(Long id);

    HeroPack findHeroPackById(Long id);

    HeroRef findRandomHeroRefByRarity(String heroRarityName);

    Hero create(Hero hero);

}
