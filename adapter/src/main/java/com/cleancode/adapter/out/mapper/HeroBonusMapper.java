package com.cleancode.adapter.out.mapper;

import com.cleancode.adapter.out.entities.HeroBonusEntity;
import com.cleancode.adapter.out.entities.HeroEntity;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroBonus;

public class HeroBonusMapper implements Mapper<HeroBonus, HeroBonusEntity> {

    private static HeroBonusMapper instance;

    private HeroBonusMapper() {}

    public static HeroBonusMapper get() {
        if (instance == null) { instance = new HeroBonusMapper(); }
        return instance;
    }

    @Override
    public HeroBonus toDomain(HeroBonusEntity entity) {
        return new HeroBonus(
            entity.getId(),
            entity.getStrong(),
            entity.getWeak(),
            entity.getBonus()
        );
    }

    @Override
    public HeroBonusEntity toEntity(HeroBonus domain) {
        HeroBonusEntity entity = new HeroBonusEntity();
        entity.setStrong(domain.getStrong());
        entity.setWeak(domain.getWeak());
        entity.setBonus(domain.getBonus());
        return entity;
    }

}
