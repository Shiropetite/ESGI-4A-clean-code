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
        if (player == null) {
            throw new RuntimeException("Le joueur " + playerId + " n'existe pas");
        }

        var pack = this.persistence.findHeroPackById(heroPackId);
        if (pack == null) {
            throw new RuntimeException("Le pack " + heroPackId + " n'existe pas");
        }

        if (!(player.canOpenHeroPack(pack))) {
            throw new RuntimeException("Le joueur " + playerId + " n'a pas assez de tokens pour ouvrir le pack " + heroPackId);
        }

        var heroes = new ArrayList<Hero>();
        for (int i = 0; i < pack.getNumberOfCards(); i++) {
            var roll = Math.random();
            if (roll < pack.getLegendaryChance()) {
                heroes.add(
                    this.persistence.createHero(new Hero(
                        this.persistence.findRandomHeroRefByRarity(new HeroLegendaryRarity().getName())
                )));
            }
            else if (roll < pack.getLegendaryChance() + pack.getRareChance()) {
                heroes.add(
                    this.persistence.createHero(new Hero(
                        this.persistence.findRandomHeroRefByRarity(new HeroRareRarity().getName())
                )));
            }
            else if (roll < pack.getLegendaryChance() + pack.getRareChance() + pack.getCommonChance()) {
                heroes.add(
                    this.persistence.createHero(new Hero(
                        this.persistence.findRandomHeroRefByRarity(new HeroCommonRarity().getName())
                )));
            }
        }
        player.openHeroPack(pack, heroes);
        this.persistence.savePlayerDeck(player);
        return heroes;
    }

}
