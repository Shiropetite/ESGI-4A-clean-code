package com.cleancode.application.ports.out;

import com.cleancode.application.ports.out.repositories.*;

public interface OpenHeroPackPersistence extends
    FindPlayerById,
    FindHeroPackById,
    FindRandomHeroRefByRarity,
    UpdatePlayer,
    CreateHero
{ }
