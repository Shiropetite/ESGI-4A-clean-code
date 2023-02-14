package com.cleancode.adapter.out.mapper;

import com.cleancode.adapter.out.entities.HeroPackEntity;
import com.cleancode.domain.HeroPack;

public class HeroPackMapper implements Mapper<HeroPack, HeroPackEntity> {

    private static HeroPackMapper instance;

    private HeroPackMapper() {}

    public static HeroPackMapper get() {
        if (instance == null) { instance = new HeroPackMapper(); }
        return instance;
    }

    @Override
    public HeroPack toDomain(HeroPackEntity entity) {
        return new HeroPack(
            entity.getName(),
            entity.getRequiredTokens(),
            entity.getNumberOfCards(),
            entity.getCommonChance(),
            entity.getRareChance(),
            entity.getLegendaryChance()
        );
    }

    @Override
    public HeroPackEntity toEntity(HeroPack domain) {
        HeroPackEntity entity = new HeroPackEntity();
        entity.setName(domain.getName());
        entity.setRequiredTokens(domain.getRequiredTokens());
        entity.setNumberOfCards(domain.getNumberOfCards());
        entity.setCommonChance(domain.getCommonChance());
        entity.setRareChance(domain.getRareChance());
        entity.setLegendaryChance(domain.getLegendaryChance());
        return entity;
    }

}
