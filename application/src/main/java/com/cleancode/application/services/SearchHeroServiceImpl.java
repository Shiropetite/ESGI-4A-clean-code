package com.cleancode.application.services;

import com.cleancode.application.ports.in.SearchHeroService;
import com.cleancode.application.ports.out.SearchHeroPersistence;
import com.cleancode.domain.HeroDuel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchHeroServiceImpl implements SearchHeroService {

    private final SearchHeroPersistence persistence;

    public SearchHeroServiceImpl(SearchHeroPersistence persistence) { this.persistence = persistence; }

    public List<HeroDuel> search(Long id) {
        var hero = this.persistence.findById(id);
        if (hero == null) {
            throw new RuntimeException("Le héros " + id + " n'existe pas");
        }
        return this.persistence.findHeroDuels(hero);
    }

}
