package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.SearchPlayerPersistence;
import com.cleancode.application.ports.out.repositories.FindPlayerByName;
import com.cleancode.domain.Player;

public record SearchPlayerPersistenceImpl(
    FindPlayerByName findPlayerByName
) implements SearchPlayerPersistence {

    @Override
    public Player findPlayerByName(String playerName) {
        return this.findPlayerByName.findPlayerByName(playerName);
    }

}
