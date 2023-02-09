package com.cleancode.adapter.out.mapper;

import com.cleancode.adapter.out.entities.RefHeroEntity;
import com.cleancode.domain.RefHero;

public class RefHeroEntityMapper {

    public static RefHero toDomain(RefHeroEntity entity) {
        return new RefHero(
            entity.getName(),
            entity.getMaxHealthPoints(),
            entity.getPowerPoints(),
            entity.getArmorPoints(),
            entity.getRarity()
        );
    }

    public static RefHeroEntity fromDomain(RefHero model) {
        RefHeroEntity entity = new RefHeroEntity();
        entity.setName(model.getName());
        entity.setMaxHealthPoints(model.getMaxHealthPoints());
        entity.setPowerPoints(model.getPowerPoints());
        entity.setArmorPoints(model.getArmorPoints());
        entity.setRarity(model.getRarity().getName());
        return entity;
    }

}
