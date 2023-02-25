package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.CreatePlayerPersistence;
import com.cleancode.application.ports.out.repositories.CreatePlayer;
import com.cleancode.application.ports.out.repositories.FindPlayerByName;
import com.cleancode.domain.Player;

public class CreatePlayerPersistenceImpl implements CreatePlayerPersistence {

    private final FindPlayerByName findPlayerByName;
    private final CreatePlayer createPlayer;

    public CreatePlayerPersistenceImpl(FindPlayerByName findPlayerByName, CreatePlayer createPlayer) {
        this.findPlayerByName = findPlayerByName;
        this.createPlayer = createPlayer;
    }

    @Override
    public Player findPlayerByName(String playerName) {
        return this.findPlayerByName.findPlayerByName(playerName);
    }

    @Override
    public Player createPlayer(Player player) {
        return this.createPlayer.createPlayer(player);
    }
}
