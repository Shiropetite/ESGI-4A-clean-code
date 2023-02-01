package com.cleancode.adapter.in;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    // 5. Rechercher des joueurs et visualiser leurs decks
    @GetMapping
    public String getPlayersWithDeck() {
        return "search user";
    }

    // 3. Création d’un compte joueur avec solde de jetons et deck en base de données
    @PostMapping
    public String createPlayer() {
        return "create user";
    }

    // 4. Ouverture de packs et ajout des cartes au deck du joueur
    @PutMapping("/{packName}")
    public String openPack(@PathVariable String packName) {
        return "open pack";
    }

}
