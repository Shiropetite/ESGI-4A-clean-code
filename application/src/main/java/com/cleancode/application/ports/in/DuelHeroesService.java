package com.cleancode.application.ports.in;

import com.cleancode.domain.HeroDuel;

import java.util.List;

public interface DuelHeroesService {

    List<HeroDuel> duel(Long player1Id, Long hero1Id, Long player2Id, Long hero2Id);

}
