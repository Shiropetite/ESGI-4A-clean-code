package com.cleancode.application.ports.in;

import com.cleancode.domain.Player;

public interface CreatePlayerService {

    Player create(String playerName);

}
