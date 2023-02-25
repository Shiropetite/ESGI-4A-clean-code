package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.application.ports.out.repositories.FindPlayerByName;
import com.cleancode.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class FindPlayerByNameImpl implements FindPlayerByName {

    private final PlayerRepository repository;

    public FindPlayerByNameImpl(PlayerRepository repository) { this.repository = repository; }

    @Override
    public Player findPlayerByName(String playerName) {
        return this.repository.findPlayerEntityByName(playerName)
            .map(playerEntity -> PlayerMapper.get().toDomain(playerEntity))
            .orElse(null);
    }

}
