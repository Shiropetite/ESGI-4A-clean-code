package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.SearchPlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public final class SearchPlayerController {

    private final SearchPlayerService service;

    public SearchPlayerController(SearchPlayerService service) { this.service = service; }

    @GetMapping("/{playerName}")
    public final ResponseEntity search(@PathVariable String playerName) {
        try {
            var player = this.service.search(playerName);
            return ResponseEntity.ok().body(player);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
