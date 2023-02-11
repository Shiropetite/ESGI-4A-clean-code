package com.cleancode.adapter.in;

import com.cleancode.application.ports.in.CreateHeroesService;
import com.cleancode.domain.HeroRef;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class CreateHeroesController {

    private final CreateHeroesService service;

    public CreateHeroesController(CreateHeroesService service) { this.service = service; }

    @PostMapping
    public List<HeroRef> create(@RequestBody List<HeroRef> heroes) {
        return this.service.create(heroes);
    }

}
