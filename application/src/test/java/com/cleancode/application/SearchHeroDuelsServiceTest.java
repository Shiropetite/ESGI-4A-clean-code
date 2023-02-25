package com.cleancode.application;

import com.cleancode.application.ports.out.SearchHeroDuelsPersistence;
import com.cleancode.application.services.SearchHeroDuelsServiceImpl;
import com.cleancode.domain.Hero;
import com.cleancode.domain.HeroDuel;
import com.cleancode.domain.HeroRef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchHeroDuelsServiceTest {

    @InjectMocks
    private SearchHeroDuelsServiceImpl service;

    @Mock
    private SearchHeroDuelsPersistence persistence;

    @Captor
    private ArgumentCaptor<Long> heroIdCaptor;

    @Captor
    private ArgumentCaptor<Hero> heroCaptor;

    @Test
    public void should_search_hero_duels() {
        final var mockHero1 = new Hero(
            1L,
            new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
            0,
            1
        );
        final var mockHero2 = new Hero(
            2L,
            new HeroRef(2L, "Mage", 700, 150, 10, "Commun"),
            0,
            1
        );
        final var expectedHeroDuels = List.of(
            new HeroDuel(1L, mockHero1, mockHero2)
        );

        when(persistence.findHeroById(mockHero1.getId())).thenReturn(mockHero1);
        when(persistence.findHeroDuelsByHero(mockHero1)).thenReturn(expectedHeroDuels);

        final var actual = service.search(mockHero1.getId());
        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expectedHeroDuels);

        verify(persistence).findHeroById(heroIdCaptor.capture());
        assertThat(heroIdCaptor.getValue())
            .isEqualTo(mockHero1.getId());

        verify(persistence).findHeroDuelsByHero(heroCaptor.capture());
        assertThat(heroCaptor.getValue())
            .usingRecursiveComparison()
            .isEqualTo(mockHero1);

        verifyNoMoreInteractions(persistence);
    }

    @Test
    public void should_throw_when_hero_not_found() {
        final var mockHeroId = 1L;

        when(persistence.findHeroById(mockHeroId)).thenReturn(null);

        assertThatExceptionOfType(RuntimeException.class)
            .isThrownBy(
                () -> this.service.search(mockHeroId)
            ).withMessage("Le héros " + mockHeroId + " n'existe pas");

        verify(persistence).findHeroById(heroIdCaptor.capture());
        assertThat(heroIdCaptor.getValue())
            .isEqualTo(mockHeroId);

        verifyNoMoreInteractions(persistence);
    }

}
