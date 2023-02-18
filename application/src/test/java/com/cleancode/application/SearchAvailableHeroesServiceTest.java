package com.cleancode.application;

import com.cleancode.application.ports.out.CreatePlayerPersistence;
import com.cleancode.application.ports.out.SearchAvailableHeroesPersistence;
import com.cleancode.application.services.CreatePlayerServiceImpl;
import com.cleancode.application.services.SearchAvailableHeroesServiceImpl;
import com.cleancode.domain.HeroRef;
import com.cleancode.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchAvailableHeroesServiceTest {

    @InjectMocks
    private SearchAvailableHeroesServiceImpl service;

    @Mock
    private SearchAvailableHeroesPersistence persistence;

    @Test
    void should_search_available_hero() {
        final var expectedHeroes = List.of(
            new HeroRef("Tank", 2000, 200, 20, "Commun"),
            new HeroRef("Tank", 2100, 210, 21, "Rare")
        );

        when(persistence.search()).thenReturn(expectedHeroes);

        final var actual = service.search();

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expectedHeroes);

        verifyNoMoreInteractions(persistence);
    }

}
