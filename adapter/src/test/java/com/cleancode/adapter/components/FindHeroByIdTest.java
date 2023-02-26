package com.cleancode.adapter.components;

import com.cleancode.adapter.out.components.FindHeroByIdImpl;
import com.cleancode.adapter.out.entities.HeroEntity;
import com.cleancode.adapter.out.entities.HeroRefEntity;
import com.cleancode.adapter.out.mapper.HeroMapper;
import com.cleancode.adapter.out.repositories.HeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class FindHeroByIdTest {

    @InjectMocks
    private FindHeroByIdImpl service;

    @Mock
    private HeroRepository heroRepository;

    @Captor
    private ArgumentCaptor<Long> heroIdCaptor;

    @Test
    void find_hero_by_id() {
        final var heroId = 1L;

        final var expectedHeroEntity = new HeroEntity();
        expectedHeroEntity.setId(heroId);
        expectedHeroEntity.setRef(new HeroRefEntity());

        final var expectedHero = HeroMapper.get().toDomain(expectedHeroEntity);

        when(heroRepository.findById(eq(heroId))).thenReturn(Optional.of(expectedHeroEntity));

        final var actual = service.findHeroById(heroId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHero);

        verify(heroRepository).findById(heroIdCaptor.capture());
        assertThat(heroIdCaptor.getValue()).isEqualTo(heroId);

        verifyNoMoreInteractions(heroRepository);
    }

}
