package com.cleancode.application.ports.out.repositories;

import com.cleancode.domain.HeroPack;

public interface FindHeroPackById {

    HeroPack findHeroPackById(Long id);

}
