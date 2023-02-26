package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.SearchAvailableHeroesService;
import com.cleancode.domain.HeroRef;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public final class SearchAvailableHeroesController {

    private final SearchAvailableHeroesService service;

    public SearchAvailableHeroesController(SearchAvailableHeroesService service) { this.service = service; }

    @GetMapping
    public final List<HeroRef> search() { return this.service.search(); }

}
