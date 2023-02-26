package com.cleancode.adapter.services;

import com.cleancode.adapter.out.services.SearchHeroDuelsPersistenceImpl;
import com.cleancode.application.ports.out.repositories.FindHeroById;
import com.cleancode.application.ports.out.repositories.FindHeroDuelsByHero;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class SearchHeroDuelsPersistenceTest {

    @InjectMocks
    private SearchHeroDuelsPersistenceImpl persistence;

    @Mock
    private FindHeroById findHeroById;

    @Mock
    private FindHeroDuelsByHero findHeroDuelsByHero;

    @Captor
    private ArgumentCaptor<Long> longArgumentCaptor;

    @Captor
    private ArgumentCaptor<Hero> heroArgumentCaptor;

    @Test
    void should_find_hero_by_id() {
        final var mockHeroId = 1L;
        final var expectedHero = new Hero(mockHeroId, null, 0, 0);

        when(findHeroById.findHeroById(mockHeroId)).thenReturn(expectedHero);

        final var actual = persistence.findHeroById(mockHeroId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHero);

        verify(findHeroById).findHeroById(longArgumentCaptor.capture());
        assertThat(longArgumentCaptor.getValue()).isEqualTo(mockHeroId);

        verifyNoMoreInteractions(findHeroById);
    }

    @Test
    void should_find_hero_duels_by_hero() {
        final var mockHero = new Hero(1L, null, 0, 0);
        final var expectedHeroDuels = List.of(
            new HeroDuel(1L, null, null)
        );

        when(findHeroDuelsByHero.findHeroDuelsByHero(eq(mockHero))).thenReturn(expectedHeroDuels);

        final var actual = persistence.findHeroDuelsByHero(mockHero);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHeroDuels);

        verify(findHeroDuelsByHero).findHeroDuelsByHero(heroArgumentCaptor.capture());
        assertThat(heroArgumentCaptor.getValue()).isEqualTo(mockHero);

        verifyNoMoreInteractions(findHeroDuelsByHero);
    }

}
