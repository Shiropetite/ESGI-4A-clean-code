package com.cleancode.application.ports.out;

import com.cleancode.application.ports.out.repositories.FindHeroById;
import com.cleancode.application.ports.out.repositories.FindHeroDuelsByHero;

public interface SearchHeroDuelsPersistence extends
    FindHeroById,
    FindHeroDuelsByHero
{ }
