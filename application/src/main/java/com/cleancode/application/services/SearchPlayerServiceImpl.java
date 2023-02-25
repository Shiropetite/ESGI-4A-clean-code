package com.cleancode.application.services;

import com.cleancode.application.ports.in.SearchPlayerService;
import com.cleancode.application.ports.out.SearchPlayerPersistence;
import com.cleancode.domain.Player;
import org.springframework.stereotype.Service;


@Service
public class SearchPlayerServiceImpl implements SearchPlayerService {

    private final SearchPlayerPersistence persistence;

    public SearchPlayerServiceImpl(SearchPlayerPersistence persistence) { this.persistence = persistence; }

    public Player search(String playerName) {
        final var player = this.persistence.findPlayerByName(playerName);
        if (player == null) {
            throw new RuntimeException("Le joueur " + playerName + " n'existe pas");
        }
        return player;
    }

}

