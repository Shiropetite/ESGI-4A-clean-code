package com.cleancode.application.services;

import com.cleancode.application.ports.in.DuelHeroesService;
import com.cleancode.application.ports.out.DuelHeroesPersistence;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.Player;
import org.springframework.stereotype.Service;

@Service
public class DuelHeroesServiceImpl implements DuelHeroesService {

    private final DuelHeroesPersistence persistence;

    public DuelHeroesServiceImpl(DuelHeroesPersistence persistence) { this.persistence = persistence; }

    @Override
    public HeroDuel duel(Long player1Id, Long hero1Id, Long player2Id, Long hero2Id) {
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

        var duel = this.duel(hero1, hero2);
        checkPlayerWins(player1);
        checkPlayerWins(player2);
        return duel;
    }

    private HeroDuel duel(Hero hero1, Hero hero2) {
        var hero1HP = hero1.getMaxHealthPoints();
        var hero2HP = hero2.getMaxHealthPoints();
        var hero1Bonus = getBonus(hero1, hero2);
        var hero2Bonus = getBonus(hero2, hero1);

        while (hero1HP > 0 && hero2HP > 0) {
            hero2HP -= hero1.getDamage(hero2, hero1Bonus);
            if (hero2HP > 0) {
                hero1HP -= hero2.getDamage(hero1, hero2Bonus);
            }
        }

        if (hero2HP <= 0) {
            hero1.gainXp();
            this.persistence.updateHero(hero1);
            return this.persistence.save(new HeroDuel(null, hero1, hero2));
        }
        else {
            hero2.gainXp();
            this.persistence.updateHero(hero2);
            return this.persistence.save(new HeroDuel(null, hero2, hero1));
        }
    }

    private int getBonus(Hero hero1, Hero hero2) {
        var heroBonus = this.persistence.findHeroBonus(hero1.getRef().getName(), hero2.getRef().getName());
        if (heroBonus == null) {
            return 0;
        }
        return heroBonus.getBonus();
    }

    private void checkPlayerWins(Player player) {
        if ((this.persistence.findVictories(player).size()+1) % 5 == 0) {
            player.addOneToken();
            this.persistence.updatePlayer(player);
        }
    }

}
