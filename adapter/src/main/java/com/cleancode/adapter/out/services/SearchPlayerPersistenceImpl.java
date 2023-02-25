package com.cleancode.adapter.out.services;

import com.cleancode.application.ports.out.SearchPlayerPersistence;
import com.cleancode.application.ports.out.repositories.FindPlayerByName;
import com.cleancode.domain.Player;

public class SearchPlayerPersistenceImpl implements SearchPlayerPersistence {

    private final FindPlayerByName findPlayerByName;

    public SearchPlayerPersistenceImpl(FindPlayerByName findPlayerByName) {
        this.findPlayerByName = findPlayerByName;
    }

    @Override
    public Player findPlayerByName(String playerName) {
        return this.findPlayerByName.findPlayerByName(playerName);
    }
}
