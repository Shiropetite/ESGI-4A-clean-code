package com.cleancode.application.ports.in;

import com.cleancode.domain.Hero;

import java.util.List;

public interface OpenHeroPackService {

    List<Hero> open(Long playerId, Long heroPackId);

}
