package com.cleancode.application.ports.out;

import com.cleancode.domain.Player;

public interface SearchPlayerPersistence {

    Player findByName(String playerName);

}
