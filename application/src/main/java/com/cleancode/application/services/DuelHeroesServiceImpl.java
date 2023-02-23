package com.cleancode.application.services;

import com.cleancode.application.ports.in.DuelHeroesService;
import com.cleancode.application.ports.out.DuelHeroesPersistence;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;
import org.springframework.stereotype.Service;

@Service
public class DuelHeroesServiceImpl implements DuelHeroesService {

    private final DuelHeroesPersistence persistence;

    public DuelHeroesServiceImpl(DuelHeroesPersistence persistence) { this.persistence = persistence; }

    @Override
    public HeroDuel duel(Long player1Id, Long hero1Id, Long player2Id, Long hero2Id) {
        var player1 = this.persistence.findPlayerById(player1Id);
        if (player1 == null) {
            throw new RuntimeException("Le joueur " + player1Id + " n'existe pas");
        }

        var player2 = this.persistence.findPlayerById(player2Id);
        if (player2 == null) {
            throw new RuntimeException("Le joueur " + player2Id + " n'existe pas");
        }

        var hero1 = this.persistence.findHeroById(hero1Id);
        if (hero1 == null) {
            throw new RuntimeException("Le héros " + hero1Id + " n'existe pas");
        }
        if (!(player1.getDeck().contains(hero1))) {
            throw new RuntimeException("Le joueur " + player1Id + " ne possède pas le héros " + hero1Id);
        }

        var hero2 = this.persistence.findHeroById(hero2Id);
        if (hero2 == null) {
            throw new RuntimeException("Le héros " + hero2Id + " n'existe pas");
        }
        if (!(player2.getDeck().contains(hero2))) {
            throw new RuntimeException("Le joueur " + player2Id + " ne possède pas le héros " + hero2Id);
        }

        if (!(hero1.canDuel(hero2))) {
            throw new RuntimeException("Le héros " + hero1Id + " ne peut pas affronter le héros " + hero2Id);
        }

        HeroDuel duel = null;

        var hero1HP = hero1.getMaxHealthPoints();
        var hero2HP = hero2.getMaxHealthPoints();

        var hero1Bonus = getBonus(hero1, hero2);
        var hero2Bonus = getBonus(hero2, hero1);

        while (duel == null) {
            hero2HP -= hero1.getDamage(hero2, hero1Bonus);

            if (hero2HP <= 0) {
                hero1.gainXp();

                if (this.persistence.getWinDuels(player1).size() % 5 == 0) {
                    player1.addOneToken();
                    this.persistence.updatePlayer(player1);
                }

                duel = this.persistence.save(new HeroDuel(null, hero1, hero2));
            }

            if (duel != null) { break; }

            hero1HP -= hero2.getDamage(hero1, hero2Bonus);

            if (hero1HP <= 0) {
                hero2.gainXp();

                if (this.persistence.getWinDuels(player2).size() % 5 == 0) {
                    player2.addOneToken();
                    this.persistence.updatePlayer(player2);
                }

                duel = this.persistence.save(new HeroDuel(null, hero2, hero1));
            }
        }

        return duel;
    }

    private int getBonus(Hero hero1, Hero hero2) {
        var heroBonus = this.persistence.findHeroBonus(hero1.getRef().getName(), hero2.getRef().getName());
        if (heroBonus != null) { return heroBonus.getBonus(); }
        return 0;
    }

}
