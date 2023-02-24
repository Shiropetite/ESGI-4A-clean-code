package com.cleancode.application.ports.in;

import com.cleancode.domain.HeroDuel;

public interface CreateHeroDuelService {

    HeroDuel create(Long player1Id, Long hero1Id, Long player2Id, Long hero2Id);

}
