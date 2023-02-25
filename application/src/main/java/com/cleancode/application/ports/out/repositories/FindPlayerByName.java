package com.cleancode.application.ports.out.repositories;

import com.cleancode.domain.Player;

public interface FindPlayerByName {

    Player findPlayerByName(String playerName);

}
