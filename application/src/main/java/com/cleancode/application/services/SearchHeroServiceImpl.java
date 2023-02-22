package com.cleancode.application.services;

import com.cleancode.application.ports.in.SearchHeroService;
import com.cleancode.application.ports.out.SearchHeroPersistence;
import com.cleancode.domain.Hero;
import org.springframework.stereotype.Service;

@Service
public class SearchHeroServiceImpl implements SearchHeroService {

    private final SearchHeroPersistence persistence;

    public SearchHeroServiceImpl(SearchHeroPersistence persistence) { this.persistence = persistence; }

    public Hero search(Long id) {
        var hero = this.persistence.findById(id);
        if (hero == null) {
            throw new RuntimeException("Le héros " + id + " n'existe pas");
        }
        return hero;
    }

}
