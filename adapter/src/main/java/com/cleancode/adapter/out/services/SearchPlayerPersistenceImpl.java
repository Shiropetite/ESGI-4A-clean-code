package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.application.ports.out.SearchPlayerPersistence;
import com.cleancode.domain.Player;

public class SearchPlayerPersistenceImpl implements SearchPlayerPersistence {

    private final PlayerRepository repository;

    public SearchPlayerPersistenceImpl(PlayerRepository repository) { this.repository = repository; }

    @Override
    public Player findByName(String playerName) {
        return this.repository.findPlayerEntityByName(playerName)
            .map(playerEntity -> PlayerMapper.get().toDomain(playerEntity))
            .orElse(null);
    }

}
