package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.application.ports.out.repositories.CreatePlayer;
import com.cleancode.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class CreatePlayerImpl implements CreatePlayer {

    private final PlayerRepository playerRepository;

    public CreatePlayerImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player createPlayer(Player player) {
        return PlayerMapper.get().toDomain(this.playerRepository.save(PlayerMapper.get().toEntity(player)));
    }

}
