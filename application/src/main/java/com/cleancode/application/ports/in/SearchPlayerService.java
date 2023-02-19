package com.cleancode.application.ports.in;

import com.cleancode.domain.Player;

public interface SearchPlayerService {

    Player search(String playerName);

}
