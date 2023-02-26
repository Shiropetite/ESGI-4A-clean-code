package com.cleancode.adapter.out.mapper;

import com.cleancode.adapter.out.entities.HeroDuelEntity;
import com.cleancode.domain.HeroDuel;

import java.util.List;
import java.util.stream.Collectors;

public final class HeroDuelMapper implements Mapper<HeroDuel, HeroDuelEntity>, ListMapper<HeroDuel, HeroDuelEntity> {

    private static HeroDuelMapper instance;

    private HeroDuelMapper() {}

    public static HeroDuelMapper get() {
        if (instance == null) { instance = new HeroDuelMapper(); }
        return instance;
    }

    public final HeroDuel toDomain(HeroDuelEntity entity) {
        return new HeroDuel(
            entity.getId(),
            HeroMapper.get().toDomain(entity.getWinner()),
            HeroMapper.get().toDomain(entity.getLoser())
        );
    }

    public final HeroDuelEntity toEntity(HeroDuel domain) {
        HeroDuelEntity entity = new HeroDuelEntity();
        entity.setWinner(HeroMapper.get().toEntity(domain.getWinner()));
        entity.setLoser(HeroMapper.get().toEntity(domain.getLoser()));
        return entity;
    }

    @Override
    public final List<HeroDuel> toDomain(List<HeroDuelEntity> entities) {
        return entities.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public final List<HeroDuelEntity> toEntity(List<HeroDuel> domains) {
        return domains.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
