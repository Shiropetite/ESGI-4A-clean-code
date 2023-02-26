package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.CreatePlayerPersistence;
import com.cleancode.application.ports.out.repositories.CreatePlayer;
import com.cleancode.application.ports.out.repositories.FindPlayerByName;
import com.cleancode.domain.Player;

public final record CreatePlayerPersistenceImpl(
    FindPlayerByName findPlayerByName,
    CreatePlayer createPlayer
) implements CreatePlayerPersistence {

    @Override
    public Player findPlayerByName(String playerName) {
        return this.findPlayerByName.findPlayerByName(playerName);
    }

    @Override
    public Player createPlayer(Player player) {
        return this.createPlayer.createPlayer(player);
    }

}
