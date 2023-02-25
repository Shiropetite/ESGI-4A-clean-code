package com.cleancode.application.ports.out.repositories;

import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.Player;

import java.util.List;

public interface FindPlayerVictories {

    List<HeroDuel> findPlayerVictories(Player player);

}
