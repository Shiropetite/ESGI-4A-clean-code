package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.application.ports.out.repositories.FindPlayerById;
import com.cleancode.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class FindPlayerByIdImpl implements FindPlayerById {

    private final PlayerRepository playerRepository;

    public FindPlayerByIdImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player findPlayerById(Long id) {
        return this.playerRepository.findById(id).map(
            playerEntity -> PlayerMapper.get().toDomain(playerEntity)
        ).orElse(null);
    }
}
