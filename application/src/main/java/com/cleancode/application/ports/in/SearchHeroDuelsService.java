package com.cleancode.application.ports.in;

import com.cleancode.domain.HeroDuel;

import java.util.List;

public interface SearchHeroDuelsService {

    List<HeroDuel> search(Long id);

}
