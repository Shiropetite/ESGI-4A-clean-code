package com.cleancode.application.ports.out;

import com.cleancode.domain.Hero;

public interface SearchHeroPersistence {

    Hero findById(Long id);

}
