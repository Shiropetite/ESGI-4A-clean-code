package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.FindAllHeroRefImpl;
import com.cleancode.adapter.out.entities.HeroRefEntity;
import com.cleancode.adapter.out.mapper.HeroRefMapper;
import com.cleancode.adapter.out.repositories.HeroRefRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindAllHeroRefTest {

    @InjectMocks
    private FindAllHeroRefImpl service;

    @Mock
    private HeroRefRepository heroRefRepository;

    @Test
    public void find_all_hero_ref() {
        final var expectedHeroRefEntities = new ArrayList<HeroRefEntity>();
        expectedHeroRefEntities.add(new HeroRefEntity());

        final var expectedHeroRef = HeroRefMapper.get().toDomain(expectedHeroRefEntities);

        when(heroRefRepository.findAll()).thenReturn(expectedHeroRefEntities);

        final var actual = service.findAllHeroRef();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroRef);

        verifyNoMoreInteractions(heroRefRepository);
    }

}
