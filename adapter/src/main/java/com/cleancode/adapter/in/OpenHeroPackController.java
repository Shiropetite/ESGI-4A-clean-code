package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.OpenHeroPackService;
import com.cleancode.domain.Hero;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class OpenHeroPackController {

    private final OpenHeroPackService service;

    public OpenHeroPackController(OpenHeroPackService service) { this.service = service; }

    @PutMapping("/{playerId}/{heroPackId}")
    public List<Hero> openPack(
        @PathVariable Long playerId,
        @PathVariable Long heroPackId
    ) {
        return this.service.open(playerId, heroPackId);
    }

}
