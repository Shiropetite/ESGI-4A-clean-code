package com.cleancode.adapter.out.mapper;

import com.cleancode.adapter.out.entities.PlayerEntity;
import com.cleancode.domain.Player;

public final class PlayerMapper implements Mapper<Player, PlayerEntity> {

    private static PlayerMapper instance;

    private PlayerMapper() {}

    public static PlayerMapper get() {
        if (instance == null) { instance = new PlayerMapper(); }
        return instance;
    }

    public final Player toDomain(PlayerEntity entity) {
        return Player.builder()
            .id(entity.getId())
            .name(entity.getName())
            .deck(HeroMapper.get().toDomain(entity.getDeck()))
            .tokens(entity.getTokens())
            .build();
    }

    public final PlayerEntity toEntity(Player domain) {
        PlayerEntity entity = new PlayerEntity();
        entity.setName(domain.getName());
        entity.setDeck(HeroMapper.get().toEntity(domain.getDeck()));
        entity.setTokens(domain.getTokens());
        return entity;
    }

}
