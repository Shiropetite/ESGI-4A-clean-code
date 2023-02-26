package com.cleancode.application.services;

import com.cleancode.application.ports.in.CreatePlayerService;
import com.cleancode.application.ports.out.CreatePlayerPersistence;
import com.cleancode.domain.Player;
import org.springframework.stereotype.Service;

@Service
public final class CreatePlayerServiceImpl implements CreatePlayerService {

    private final CreatePlayerPersistence persistence;

    public CreatePlayerServiceImpl(CreatePlayerPersistence persistence) { this.persistence = persistence; }

    public final Player create(String playerName) {
        final var playerWithSameName = this.persistence.findPlayerByName(playerName);

        if (playerWithSameName != null) {
            throw new RuntimeException("Le joueur " + playerName + " existe déjà");
        }

        return this.persistence.createPlayer(Player.builder().name(playerName).build());
    }

}
