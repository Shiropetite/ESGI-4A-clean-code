package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.OpenHeroPackService;
import com.cleancode.domain.Hero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class OpenHeroPackController {

    private final OpenHeroPackService service;

    public OpenHeroPackController(OpenHeroPackService service) { this.service = service; }

    @PutMapping("/{playerId}/{heroPackId}")
    public ResponseEntity openPack(
        @PathVariable Long playerId,
        @PathVariable Long heroPackId
    ) {
        try {
            var heroes = this.service.open(playerId, heroPackId);
            return ResponseEntity.ok().body(heroes);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
