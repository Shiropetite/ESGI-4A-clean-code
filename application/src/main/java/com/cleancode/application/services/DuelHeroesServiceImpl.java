package com.cleancode.application.services;

import com.cleancode.application.ports.in.DuelHeroesService;
import com.cleancode.application.ports.out.DuelHeroesPersistence;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DuelHeroesServiceImpl implements DuelHeroesService {

    private final DuelHeroesPersistence persistence;

    public DuelHeroesServiceImpl(DuelHeroesPersistence persistence) { this.persistence = persistence; }

    @Override
    public List<HeroDuel> duel(Long player1Id, Long hero1Id, Long player2Id, Long hero2Id) {
        var player1 = this.persistence.findPlayerById(player1Id);
        var hero1 = this.persistence.findHeroById(hero1Id);
        var player2 = this.persistence.findPlayerById(player2Id);
        var hero2 = this.persistence.findHeroById(hero2Id);

        if (player1 == null) {
            throw new RuntimeException("Le joueur " + player1Id + " n'existe pas");
        }
        if (player2 == null) {
            throw new RuntimeException("Le joueur " + player2Id + " n'existe pas");
        }
        if (hero1 == null) {
            throw new RuntimeException("Le héros " + hero1Id + " n'existe pas");
        }
        if (!(player1.getDeck().contains(hero1))) {
            throw new RuntimeException("Le joueur " + player1Id + " ne possède pas le héros " + hero1Id);
        }
        if (hero2 == null) {
            throw new RuntimeException("Le héros " + hero2Id + " n'existe pas");
        }
        if (!(player2.getDeck().contains(hero2))) {
            throw new RuntimeException("Le joueur " + player2Id + " ne possède pas le héros " + hero2Id);
        }
        if (!(hero1.canDuel(hero2))) {
            throw new RuntimeException("Le héros " + hero1Id + " ne peut pas affronter le héros " + hero2Id);
        }

        this.duel(hero1, hero2);
        this.persistence.updateHero(hero1);
        this.persistence.updateHero(hero2);
        return List.of();
    }

    private void duel(Hero hero1, Hero hero2) {
        var hero1HP = hero1.getMaxHealthPoints();
        var hero2HP = hero2.getMaxHealthPoints();

        var hero1Bonus = 0;
        var hero2Bonus = 0;
        var heroBonus = this.persistence.findHeroBonus(hero1.getRef().getName(), hero2.getRef().getName());
        if (heroBonus == null) {
            heroBonus = this.persistence.findHeroBonus(hero2.getRef().getName(), hero1.getRef().getName());
            if (heroBonus != null) {
                hero2Bonus = heroBonus.getBonus();
            }
        }
        else {
            hero1Bonus = heroBonus.getBonus();
        }

        while (hero1HP > 0 && hero2HP > 0) {
            hero2HP -= hero1.getPowerPoints() - hero2.getArmorPoints() + hero1Bonus;
            hero1HP -= hero2.getPowerPoints() - hero1.getArmorPoints() + hero2Bonus;
        }
        if (hero2HP <= 0) {
            this.endDuel(hero1, hero2);
        }
        else {
            this.endDuel(hero2, hero1);
        }
    }

    private void endDuel(Hero winner, Hero loser) {
        winner.gainXp();
        winner.getDuels().add(new HeroDuel(loser, true));
        loser.getDuels().add(new HeroDuel(winner, false));
    }

}
