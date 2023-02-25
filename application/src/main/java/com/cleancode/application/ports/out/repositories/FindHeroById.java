package com.cleancode.application.ports.out.repositories;

import com.cleancode.domain.Hero;

public interface FindHeroById {

    Hero findHeroById(Long id);

}
