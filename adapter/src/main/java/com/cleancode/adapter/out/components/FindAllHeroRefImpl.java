package com.cleancode.adapter.out.components;

import com.cleancode.adapter.out.mapper.HeroRefMapper;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import com.cleancode.application.ports.out.repositories.FindAllHeroRef;
import com.cleancode.domain.HeroRef;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAllHeroRefImpl implements FindAllHeroRef {

    private final HeroRefRepository repository;

    public FindAllHeroRefImpl(HeroRefRepository repository) { this.repository = repository; }

    @Override
    public List<HeroRef> findAllHeroRef() {
        return this.repository.findAll()
            .stream()
            .map(heroRefEntity -> HeroRefMapper.get().toDomain(heroRefEntity))
            .collect(Collectors.toList());
    }

}
