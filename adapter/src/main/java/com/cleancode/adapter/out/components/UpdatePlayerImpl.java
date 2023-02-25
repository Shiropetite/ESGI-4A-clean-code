package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.mapper.PlayerMapper;
import com.cleancode.adapter.out.repositories.PlayerRepository;
import com.cleancode.application.ports.out.repositories.UpdatePlayer;
import com.cleancode.domain.Hero;
import com.cleancode.domain.Player;
import org.springframework.stereotype.Component;

@Component
public class UpdatePlayerImpl implements UpdatePlayer {

    private final PlayerRepository playerRepository;

    public UpdatePlayerImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player updatePlayer(Player player) {
        final var playerEntity = this.playerRepository.findById(player.getId());
        if (playerEntity.isEmpty()) { return null; }

        final var entity = playerEntity.get();
        entity.setName(player.getName());
        entity.setTokens(player.getTokens());

        for (Hero hero: player.getDeck()) {
            if (!(entity.getDeck().contains(HeroMapper.get().toEntity(hero)))) {
                entity.getDeck().add(HeroMapper.get().toEntity(hero));
            }
        }

        final var saveEntity = this.playerRepository.save(entity);
        return PlayerMapper.get().toDomain(saveEntity);
    }

}
