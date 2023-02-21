package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.DuelHeroesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heroes")
public class DuelHeroesController {

    private final DuelHeroesService service;

    public DuelHeroesController(DuelHeroesService service) { this.service = service; }

    @PutMapping()
    public ResponseEntity search(@RequestBody DuelHeroesRequestBody duelSettings) {
        try {
            var duels = this.service.duel(
                duelSettings.player1Id,
                duelSettings.hero1Id,
                duelSettings.player2Id,
                duelSettings.hero2Id
            );
            return ResponseEntity.ok().body(duels);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
