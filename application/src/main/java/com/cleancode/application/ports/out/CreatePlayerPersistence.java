package com.cleancode.application.ports.out;

import com.cleancode.domain.Player;

public interface CreatePlayerPersistence {

    Player findByName(String playerName);

    Player create(Player player);

}
