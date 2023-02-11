package com.cleancode.application.ports.out;

import com.cleancode.domain.Player;

public interface CreatePlayerPersistence {

    Player create(Player player);

}
