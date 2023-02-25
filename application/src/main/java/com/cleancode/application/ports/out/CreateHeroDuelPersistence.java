package com.cleancode.application.ports.out;

import com.cleancode.application.ports.out.repositories.*;

public interface CreateHeroDuelPersistence extends
    FindPlayerById,
    FindHeroById,
    FindHeroBonus,
    FindPlayerVictories,
    UpdateHero,
    UpdatePlayer,
    CreateHeroDuel
{ }
