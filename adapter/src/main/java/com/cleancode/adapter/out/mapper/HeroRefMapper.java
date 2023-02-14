package com.cleancode.adapter.out.mapper;

import com.cleancode.adapter.out.entities.HeroRefEntity;
import com.cleancode.domain.HeroRef;

import java.util.List;
import java.util.stream.Collectors;

public class HeroRefMapper implements Mapper<HeroRef, HeroRefEntity>, ListMapper<HeroRef, HeroRefEntity> {

    private static HeroRefMapper instance;

    private HeroRefMapper() {}

    public static HeroRefMapper get() {
        if (instance == null) { instance = new HeroRefMapper(); }
        return instance;
    }

    public HeroRef toDomain(HeroRefEntity entity) {
        return new HeroRef(
            entity.getName(),
            entity.getMaxHealthPoints(),
            entity.getPowerPoints(),
            entity.getArmorPoints(),
            entity.getRarity()
        );
    }

    public HeroRefEntity toEntity(HeroRef domain) {
        HeroRefEntity entity = new HeroRefEntity();
        entity.setName(domain.getName());
        entity.setMaxHealthPoints(domain.getMaxHealthPoints());
        entity.setPowerPoints(domain.getPowerPoints());
        entity.setArmorPoints(domain.getArmorPoints());
        entity.setRarity(domain.getRarity().getName());
        return entity;
    }

    @Override
    public List<HeroRef> toDomain(List<HeroRefEntity> entities) {
        return entities.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<HeroRefEntity> toEntity(List<HeroRef> domains) {
        return domains.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
