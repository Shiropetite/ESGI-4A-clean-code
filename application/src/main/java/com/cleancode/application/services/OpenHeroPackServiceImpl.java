package com.cleancode.application.services;

import com.cleancode.application.ports.in.OpenHeroPackService;
import com.cleancode.application.ports.out.OpenHeroPackPersistence;
import com.cleancode.domain.Hero;
import com.cleancode.domain.rarity.HeroCommonRarity;
import com.cleancode.domain.rarity.HeroLegendaryRarity;
import com.cleancode.domain.rarity.HeroRareRarity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenHeroPackServiceImpl implements OpenHeroPackService {

    private final OpenHeroPackPersistence persistence;

    public OpenHeroPackServiceImpl(OpenHeroPackPersistence persistence) { this.persistence = persistence; }

    public List<Hero> open(Long playerId, Long heroPackId) {
        var player = this.persistence.findPlayerById(playerId);
        var pack = this.persistence.findHeroPackById(heroPackId);

        if (!(player.canOpenHeroPack(pack))) {
            throw new RuntimeException("Le joueur " + playerId + "n'a pas assez de token pour ouvrir le pack " + heroPackId);
        }

        var heroes = new ArrayList<Hero>();
        for (int i = 0; i < pack.getNumberOfCards(); i++) {
            var roll = Math.random();
            if (roll < pack.getLegendaryChance()) {
                var createdHero = this.persistence.create(new Hero(this.persistence.findRandomHeroRefByRarity(new HeroLegendaryRarity().getName())));
                heroes.add(createdHero);
            }
            else if (roll < pack.getLegendaryChance() + pack.getRareChance()) {
                var createdHero = this.persistence.create(new Hero(this.persistence.findRandomHeroRefByRarity(new HeroRareRarity().getName())));
                heroes.add(createdHero);
            }
            else if (roll < pack.getLegendaryChance() + pack.getRareChance() + pack.getCommonChance()) {
                var createdHero = this.persistence.create(new Hero(this.persistence.findRandomHeroRefByRarity(new HeroCommonRarity().getName())));
                heroes.add(createdHero);
            }
        }

        player.openHeroPack(pack, heroes);

        return heroes;
    }

}