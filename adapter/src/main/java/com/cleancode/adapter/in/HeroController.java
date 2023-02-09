package com.cleancode.adapter.in;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    // 2. Rechercher les héros disponibles
    @GetMapping
    public String searchAvailableHeroes() { return "search available heroes"; }

    // 7. Pouvoir retrouver tous les combats d’un héros (héros adverse, résultat du combat)
    @GetMapping("/{heroId}")
    public String getHeroFights(@PathVariable String heroId) {
        return "get hero fights";
    }

    // 6. Engager un combat entre un héros et celui d’un autre joueur
    @PutMapping("/{targetId}")
    public String beginFight(@PathVariable String targetId) {
        return "begin fight";
    }

}
