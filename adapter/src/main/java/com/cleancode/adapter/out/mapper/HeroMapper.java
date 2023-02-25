package com.cleancode.adapter.out.mapper;

import com.cleancode.adapter.out.entities.HeroEntity;
import com.cleancode.adapter.out.entities.HeroRefEntity;
import com.cleancode.domain.Hero;

import java.util.List;
import java.util.stream.Collectors;

public class HeroMapper implements Mapper<Hero, HeroEntity>, ListMapper<Hero, HeroEntity> {

    private static HeroMapper instance;

    private HeroMapper() {}

    public static HeroMapper get() {
        if (instance == null) { instance = new HeroMapper(); }
        return instance;
    }

    public Hero toDomain(HeroEntity entity) {
        return new Hero(
            entity.getId(),
            HeroRefMapper.get().toDomain(entity.getRef()),
            entity.getXp(),
            entity.getLevel()
        );
    }

    public HeroEntity toEntity(Hero domain) {
        HeroEntity entity = new HeroEntity();
        entity.setId(domain.getId());
        entity.setRef(HeroRefMapper.get().toEntity(domain.getRef()));
        entity.setXp(domain.getXp());
        entity.setLevel(domain.getLevel());
        return entity;
    }

    public HeroEntity toEntity(Hero domain, HeroRefEntity refEntity) {
        HeroEntity entity = new HeroEntity();
        entity.setRef(refEntity);
        entity.setXp(domain.getXp());
        entity.setLevel(domain.getLevel());
        return entity;
    }

    @Override
    public List<Hero> toDomain(List<HeroEntity> entities) {
        return entities.stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<HeroEntity> toEntity(List<Hero> domains) {
        return domains.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
