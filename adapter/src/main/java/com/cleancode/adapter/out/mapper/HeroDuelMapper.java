package com.cleancode.adapter.out.mapper;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.domain.HeroDuel;

import java.util.List;
import java.util.stream.Collectors;

public class HeroDuelMapper implements Mapper<HeroDuel, HeroDuelEntity>, ListMapper<HeroDuel, HeroDuelEntity> {

    private static HeroDuelMapper instance;

    private HeroDuelMapper() {}

    public static HeroDuelMapper get() {
        if (instance == null) { instance = new HeroDuelMapper(); }
        return instance;
    }

    public HeroDuel toDomain(HeroDuelEntity entity) {
        return new HeroDuel(
            HeroMapper.get().toDomain(entity.getOpponent()),
            entity.isVictory()
        );
    }

    public HeroDuelEntity toEntity(HeroDuel domain) {
        HeroDuelEntity entity = new HeroDuelEntity();
        entity.setOpponent(HeroMapper.get().toEntity(domain.getOpponent()));
        entity.setVictory(domain.isVictory());
        return entity;
    }

    @Override
    public List<HeroDuel> toDomain(List<HeroDuelEntity> entities) {
        return entities.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<HeroDuelEntity> toEntity(List<HeroDuel> domains) {
        return domains.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
