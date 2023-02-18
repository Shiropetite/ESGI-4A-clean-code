package com.cleancode.application.services;

import com.cleancode.application.ports.in.CreatePlayerService;
import com.cleancode.application.ports.out.CreatePlayerPersistence;
import com.cleancode.domain.Player;
import org.springframework.stereotype.Service;

@Service
public class CreatePlayerServiceImpl implements CreatePlayerService {

    private final CreatePlayerPersistence persistence;

    public CreatePlayerServiceImpl(CreatePlayerPersistence persistence) { this.persistence = persistence; }

    public Player create(String playerName) { return this.persistence.create(Player.builder().name(playerName).build()); }

}
