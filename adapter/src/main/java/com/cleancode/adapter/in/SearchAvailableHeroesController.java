package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.SearchAvailableHeroesService;
import com.cleancode.domain.RefHero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class SearchAvailableHeroesController {

    private final SearchAvailableHeroesService service;

    public SearchAvailableHeroesController(SearchAvailableHeroesService service) { this.service = service; }

    @GetMapping
    public List<RefHero> search() { return this.service.search(); }

}
