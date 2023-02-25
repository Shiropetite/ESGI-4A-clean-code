package com.cleancode.application;

import com.cleancode.application.ports.out.SearchAvailableHeroesPersistence;
import com.cleancode.application.services.SearchAvailableHeroesServiceImpl;
import com.cleancode.domain.HeroRef;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchAvailableHeroesServiceTest {

    @InjectMocks
    private SearchAvailableHeroesServiceImpl service;

    @Mock
    private SearchAvailableHeroesPersistence persistence;

    @Test
    public void should_search_available_heroes_ref() {
        final var expectedHeroes = List.of(
            new HeroRef(1L, "Tank", 1200, 120, 22, "Légendaire"),
            new HeroRef(2L, "Mage", 700, 150, 10, "Commun")
        );

        when(persistence.findAllHeroRef()).thenReturn(expectedHeroes);

        final var actual = service.search();
        assertThat(actual)
            .usingRecursiveComparison()
            .isEqualTo(expectedHeroes);

        verifyNoMoreInteractions(persistence);
    }

}
