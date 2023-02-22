package com.cleancode.application.ports.in;

import com.cleancode.domain.Hero;

public interface SearchHeroService {

    Hero search(Long id);

}
