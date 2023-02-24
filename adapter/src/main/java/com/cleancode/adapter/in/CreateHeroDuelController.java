package com.cleancode.adapter.in;

import com.cleancode.adapter.in.body.DuelHeroesRequestBody;
import com.cleancode.application.ports.in.CreateHeroDuelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heroes")
public class CreateHeroDuelController {

    private final CreateHeroDuelService service;

    public CreateHeroDuelController(CreateHeroDuelService service) { this.service = service; }

    @PutMapping()
    public ResponseEntity create(@RequestBody DuelHeroesRequestBody duelSettings) {
        try {
            var duel = this.service.create(
                duelSettings.player1Id,
                duelSettings.hero1Id,
                duelSettings.player2Id,
                duelSettings.hero2Id
            );
            return ResponseEntity.ok().body(duel);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
