package com.cleancode.adapter.out.services;

import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.application.ports.out.CreatePlayerPersistence;
import com.cleancode.domain.Player;

public class CreatePlayerPersistenceImpl implements CreatePlayerPersistence {

    private final PlayerRepository repository;

    public CreatePlayerPersistenceImpl(PlayerRepository repository) { this.repository = repository; }

    @Override
    public Player findByName(String playerName) {
        return this.repository.findPlayerEntityByName(playerName)
            .map(playerEntity -> PlayerMapper.get().toDomain(playerEntity))
            .orElse(null);
    }

    @Override
    public Player create(Player player) {
        var entity = this.repository.save(PlayerMapper.get().toEntity(player));
        return PlayerMapper.get().toDomain(entity);
    }

}
