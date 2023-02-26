package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.SearchHeroDuelsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hero-duels")
public final class SearchHeroDuelsController {

    private final SearchHeroDuelsService service;

    public SearchHeroDuelsController(SearchHeroDuelsService service) { this.service = service; }

    @GetMapping("/{heroId}")
    public final ResponseEntity search(@PathVariable Long heroId) {
        try {
            var hero = this.service.search(heroId);
            return ResponseEntity.ok().body(hero);
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
